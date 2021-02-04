package com.github.wcosmedlr.services;

import com.github.wcosmedlr.models.Balance;
import com.github.wcosmedlr.models.Transaction;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class TransactionService implements TransactionServiceI {

    private final BalanceServiceI balanceService;

    @Inject
    public TransactionService(BalanceServiceI balanceService) {
        this.balanceService = balanceService;
    }

    @Override
    public Single<List<Transaction>> findAll() {
        List<Balance> balances = balanceService.findAll().blockingGet();
        return Single.just(transactions(balances));
    }

    public List<Transaction> transactions(List<Balance> balances){
        List<Transaction> transactions = new ArrayList<>(balances.size());
        if(balances.size() > 1){
            Balance maxCredit;
            Balance maxDebit;
            Double minimum;
            boolean thereIsTransaction;
            do {
                maxCredit = getMaxCredit(balances);
                maxDebit = getMaxDebit(balances);
                minimum = getMinimum(maxCredit, maxDebit);
                thereIsTransaction = maxCredit.getValue() != 0 && maxDebit.getValue() != 0;
                if(thereIsTransaction) {
                    maxCredit.setValue(maxCredit.getValue() - minimum);
                    maxDebit.setValue(maxDebit.getValue() + minimum);
                    transactions.add(new Transaction
                            .Builder()
                            .setOwner(maxDebit.getOwner())
                            .setBenefactor(maxCredit.getOwner())
                            .setValue(minimum)
                            .build());
                }
            } while(thereIsTransaction);
        }
        return transactions;
    }

    public Balance getMaxCredit(List<Balance> balances){
        return balances.stream().max(java.util.Comparator.naturalOrder()).orElse(null);
    }

    public Balance getMaxDebit(List<Balance> balances){
        return balances.stream().sorted().findFirst().orElse(null);
    }

    public Double getMinimum(Balance credit, Balance debit){
        return credit.getValue() <= -debit.getValue()
                ? credit.getValue()
                : -debit.getValue();
    }
}
