package com.github.wcosmedlr.services;

import com.github.wcosmedlr.dto.Expense;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

public interface ExpenseServiceI {
    Single<List<Expense>> findAllOrderByTimeStampDesc();
    Maybe<Long> add(Expense expenseModel);
    Maybe<Expense> findById(Long id);
}
