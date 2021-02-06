package com.github.wcosmedlr.specs.controllers

import com.github.wcosmedlr.clients.ExpenseClient
import com.github.wcosmedlr.dto.Expense
import com.github.wcosmedlr.mothers.ExpenseMother
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.reactivex.Maybe
import io.reactivex.Single
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject
import java.util.stream.Collectors

@MicronautTest
class ExpenseEntityControllerSpec extends Specification {

    @Inject
    @Shared
    @AutoCleanup
    private EmbeddedApplication<?> application

    private ExpenseClient client = application.applicationContext.getBean(ExpenseClient)

    @Inject
    @Shared
    private ExpenseMother expenseMother;

    /*
        When the user sends a request the expenses
        Then the system must return all expenses
    */
    void 'get all the expenses'() {
        when:
        Single<List<Expense>> result = client.findAllOrderByTimeStamp();

        then:
        List<Expense> expensesObtained = result.blockingGet()
        expensesObtained.toSet() == expenseMother.createAListOfExpensesToCheckBalances().toSet()
    }

    /*
         When the user sends a request the expenses
         Then the system must return all expenses sorted
     */
    void 'get the expenses sorted'() {
        when:
        Single<List<Expense>> result = client.findAllOrderByTimeStamp();

        then:
        List<Expense> expensesObtained = result.blockingGet()
        expensesObtained == expenseMother.createAListOfExpensesToCheckBalances()
                .stream()
                .sorted(Expense.compareByTimeStamp)
                .collect(Collectors.toList())
    }

    /*
        When the user sends a request to add an expense
        Then the system must add expense
    */
    void 'add an expense'() {
        given:
        Expense expense = expenseMother.createValidExpense1()

        when:
        final Maybe<Long> result = client.add(expense);
        Long id = result.blockingGet();

        and:
        Expense expenseObtained = client.findById(id).blockingGet();

        then:
        expense == expenseObtained;
    }


}
