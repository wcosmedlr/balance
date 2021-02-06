package com.github.wcosmedlr.specs.repositories

import com.github.wcosmedlr.dto.Expense
import com.github.wcosmedlr.mothers.ExpenseMother
import com.github.wcosmedlr.mothers.MemberMother
import com.github.wcosmedlr.services.ExpenseService
import com.github.wcosmedlr.services.MemberService
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.reactivex.Maybe
import io.reactivex.Single
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject
import java.util.stream.Collectors
import java.util.stream.IntStream

@MicronautTest
class ExpenseEntityServiceSpec extends Specification{

    @Inject
    @Shared
    @AutoCleanup
    private EmbeddedApplication<?> application

    private ExpenseService expenseService = application.applicationContext.getBean(ExpenseService)

    @Inject
    @Shared
    private ExpenseMother expenseMother;

    private MemberService memberService = application.applicationContext.getBean(MemberService)

    @Inject
    @Shared
    private MemberMother memberMother;

    /*
        Given a subset of expenses with registered members
        When the user sends a request the expenses
        Then the system must return all expenses
    */
    void 'get all the expenses'() {
        given:
        List<Expense> expenses = expenseMother.createAListOfExpensesToCheckBalances()
        expenses.forEach((Expense e) -> {
            Long id = memberService.add(e.getOwner()).blockingGet()
            e.getOwner().setId(id)
            expenseService.add(e)
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
            expenseService.add(e)
        })
        IntStream.rangeClosed(0,expenses.size() -1 )
                .forEach(i -> expenses.get(i).setId(i+1))

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
        Long id = result.blockingGet();

        and:
        Expense expenseObtained = expenseService.findById(id).blockingGet();

        then:
        expense == expenseObtained;
    }

}
