package com.github.wcosmedlr.domain.models;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@SuperBuilder(setterPrefix = "set", toBuilder = true)
public class BalanceStatus {

    @NonNull
    List<BalanceMember> balanceMembers;

    public List<Transaction> calculateTransactions(){
        List<BalanceMember> balanceStatusesCopy = balanceMembers.stream().map(b -> b.toBuilder().build()).collect(Collectors.toList());
        List<Transaction> transactions = new ArrayList<>(balanceStatusesCopy.size());
        if(balanceStatusesCopy.size() > 1){
            BalanceMember maxCredit;
            BalanceMember maxDebit;
            Double minimum;
            boolean thereIsTransaction;
            do {
                maxCredit = getMaxCredit(balanceStatusesCopy);
                maxDebit = getMaxDebit(balanceStatusesCopy);
                minimum = getMinimum(maxCredit, maxDebit);
                thereIsTransaction = maxCredit.getValue() != 0 && maxDebit.getValue() != 0;
                if(thereIsTransaction) {
                    maxCredit.setValue(maxCredit.getValue() - minimum);
                    maxDebit.setValue(maxDebit.getValue() + minimum);
                    transactions.add(Transaction.builder()
                            .setOwner(maxDebit.getOwner())
                            .setBenefactor(maxCredit.getOwner())
                            .setValue(minimum)
                            .build());
                }
            } while(thereIsTransaction);
        }
        return transactions;
    }

    protected BalanceMember getMaxCredit(List<BalanceMember> balanceMembers){
        return balanceMembers.stream().max(java.util.Comparator.naturalOrder()).orElse(null);
    }

    protected BalanceMember getMaxDebit(List<BalanceMember> balanceMembers){
        return balanceMembers.stream().sorted().findFirst().orElse(null);
    }

    protected Double getMinimum(BalanceMember credit, BalanceMember debit){
        return credit.getValue() <= -debit.getValue()
                ? credit.getValue()
                : -debit.getValue();
    }

}
