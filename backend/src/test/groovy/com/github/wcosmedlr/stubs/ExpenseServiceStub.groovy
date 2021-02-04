package com.github.wcosmedlr.stubs


import com.github.wcosmedlr.models.Expense
import com.github.wcosmedlr.mothers.ExpenseMother
import com.github.wcosmedlr.services.ExpenseServiceI
import io.micronaut.context.annotation.Primary
import io.reactivex.Maybe
import io.reactivex.Single

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Primary
class ExpenseServiceStub implements ExpenseServiceI {

    ExpenseMother expenseMother;

    @Inject
    ExpenseServiceStub(ExpenseMother expenseMother) {
        this.expenseMother = expenseMother
    }

    @Override
    Single<List<Expense>> findAllOrderByTimeStampDesc() {
        List<Expense> expenses = expenseMother.createAListOfExpensesToCheckBalances()
        expenses.sort(Expense.compareByTimeStamp)
        return Single.just(expenses);
    }

    @Override
    Maybe<Long> add(Expense expense) {
        if (expenseMother.createValidExpense1() == expense) {
            return Maybe.just(1)
        } else {
            return Maybe.empty()
        }
    }

    @Override
    Maybe<Expense> findById(Long id) {
        if (id == 1) {
            return Maybe.just(expenseMother.createValidExpense1())
        } else {
            return Maybe.empty()
        }
    }
}
