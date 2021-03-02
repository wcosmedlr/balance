package com.github.wcosmedlr.adapters.secondary.persistence

import com.github.wcosmedlr.domain.models.Expense
import com.github.wcosmedlr.domain.services.ExpenseService
import com.github.wcosmedlr.domain.services.MemberService
import com.github.wcosmedlr.helpers.mothers.ExpenseMother
import com.github.wcosmedlr.helpers.specs.IntegrationBase
import io.reactivex.Maybe
import io.reactivex.Single
import spock.lang.Shared

import javax.inject.Inject
import javax.transaction.Transactional
import java.util.stream.Collectors

class ExpenseRepositorySpecIT extends IntegrationBase{

    private ExpenseService expenseService = getBean(ExpenseService)
    private MemberService memberService = getBean(MemberService)

    @Inject
    @Shared
    private ExpenseMother expenseMother

    /*
        Given a subset of expenses with registered members
        When the user sends a request the expenses
        Then the system must return all expenses
    */
    @Transactional
    void 'get all the expenses'() {
        given:
        List<Expense> expenses = expenseMother.createAListOfExpensesToCheckBalances()
        expenses.forEach((Expense e) -> {
            Long id = memberService.add(e.getOwner()).blockingGet()
            e.getOwner().setId(id)
            Long expensesId = expenseService.add(e).blockingGet()
            e.setId(expensesId)
        })

        when:
        Single<List<Expense>> result = expenseService.findAllOrderByTimeStampDesc()

        then:
        List<Expense> expensesObtained = result.blockingGet()
        expensesObtained.toSet() == expenses.toSet()
    }

    /*
         Given a subset of expenses with registered members
         When the user sends a request the expenses
         Then the system must return all expenses sorted
     */
    void 'get the expenses sorted'() {
        given:
        List<Expense> expenses = expenseMother.createAListOfExpensesToCheckBalances()
        expenses.forEach((Expense e) -> {
            Long id = memberService.add(e.getOwner()).blockingGet()
            e.getOwner().setId(id)
            Long expensesId = expenseService.add(e).blockingGet()
            e.setId(expensesId)
        })

        when:
        Single<List<Expense>> result = expenseService.findAllOrderByTimeStampDesc();

        then:
        List<Expense> expensesObtained = result.blockingGet()
        expensesObtained == expenses.stream()
                .sorted(Expense.compareByTimeStamp.reversed())
                .collect(Collectors.toList())
    }

    /*
        Given an expense with registered members
        When the user sends a request to add an expense
        Then the system must add expense
    */
    void 'add an expense'() {
        given:
        Expense expense = expenseMother.createValidExpense1()
        Long memberId = memberService.add(expense.getOwner()).blockingGet()
        expense.getOwner().setId(memberId)

        when:
        final Maybe<Long> result = expenseService.add(expense);
        Long expenseId = result.blockingGet();
        expense.setId(expenseId)

        and:
        Expense expenseObtained = expenseService.findById(expenseId).blockingGet();

        then:
        expense == expenseObtained;
    }

}
