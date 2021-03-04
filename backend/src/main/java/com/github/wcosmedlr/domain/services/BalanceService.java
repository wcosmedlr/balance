package com.github.wcosmedlr.domain.services;

import com.github.wcosmedlr.domain.models.Balance;
import com.github.wcosmedlr.domain.models.BalanceStatus;
import com.github.wcosmedlr.domain.models.Expense;
import com.github.wcosmedlr.domain.models.Member;
import com.github.wcosmedlr.ports.primary.services.BalanceServiceI;
import com.github.wcosmedlr.ports.primary.services.ExpenseServiceI;
import com.github.wcosmedlr.ports.primary.services.MemberServiceI;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class BalanceService implements BalanceServiceI {

    private final ExpenseServiceI expenseService;
    private final MemberServiceI memberService;

    @Inject
    public BalanceService(ExpenseServiceI expenseService, MemberServiceI memberService) {
        this.expenseService = expenseService;
        this.memberService = memberService;
    }

    @Override
    public Single<BalanceStatus> findAllBalanceStatus() {
        List<Member> members = memberService.findAll().blockingGet();
        List<Expense> expenses = expenseService.findAllOrderByTimeStampDesc().blockingGet();
        Balance balance = Balance.builder()
                .setMembers(members)
                .setExpenses(expenses)
                .build();
        return Single.just(balance.calculateBalanceStatus());
    }

}
