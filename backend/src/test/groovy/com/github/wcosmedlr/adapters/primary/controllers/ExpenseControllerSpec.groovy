package com.github.wcosmedlr.adapters.primary.controllers

import com.github.wcosmedlr.domain.models.Expense
import com.github.wcosmedlr.helpers.clients.ExpenseClient
import com.github.wcosmedlr.helpers.mothers.ExpenseMother
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.reactivex.Maybe
import io.reactivex.Single
import spock.lang.Specification

import javax.inject.Inject
import java.util.stream.Collectors

@MicronautTest
class ExpenseControllerSpec extends Specification {

    @Inject
    private ExpenseClient client

    @Inject
    private ExpenseMother expenseMother

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
        Long expenseId = result.blockingGet();
        expense.setId(expenseId)

        and:
        Expense expenseObtained = client.findById(expenseId).blockingGet();

        then:
        expense == expenseObtained;
    }


}
