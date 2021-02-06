package com.github.wcosmedlr.services;

import com.github.wcosmedlr.dto.Balance;
import com.github.wcosmedlr.dto.Expense;
import com.github.wcosmedlr.dto.Member;
import com.github.wcosmedlr.dto.MoneyUnit;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class BalanceService implements BalanceServiceI{

    private final ExpenseServiceI expenseService;
    private final MemberServiceI memberService;

    @Inject
    public BalanceService(ExpenseServiceI expenseService, MemberServiceI memberService) {
        this.expenseService = expenseService;
        this.memberService = memberService;
    }

    @Override
    public Single<List<Balance>> findAll() {
        List<Member> members = memberService.findAll().blockingGet();
        List<Expense> expenses = expenseService.findAllOrderByTimeStampDesc().blockingGet();
        return Single.just(balances(members,expenses));
    }

    public List<Balance> balances(List<Member> members, List<Expense> expenses){
        final List<Balance> totalSpentByMember = new ArrayList<>();
        final List<Balance> balances = new ArrayList<>();

        members.stream().forEach(m ->
            totalSpentByMember.add(
                    Balance.builder()
                            .setOwner(m)
                            .setValue(expenses.stream()
                                    .filter(e -> e.getOwner().equals(m))
                                    .map(MoneyUnit::getValue)
                                    .reduce(0D, Double::sum))
                            .build()
            )
        );

        final Double totalCost = totalSpentByMember.stream()
                .map(Balance::getValue)
                .reduce(0D, Double::sum);
        final Double equalCost = totalCost / members.size();

        totalSpentByMember.stream().forEach(b ->
            balances.add(
                    Balance.builder()
                            .setOwner(b.getOwner())
                            .setValue(b.getValue() - equalCost)
                            .build()
            )
        );

        return balances;
    }
}
