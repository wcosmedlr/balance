package com.github.wcosmedlr.ports.secondary.repositories;

import com.github.wcosmedlr.domain.models.Expense;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

public interface ExpenseRepositoryI {
    Single<List<Expense>> findAllOrderByTimeStampDesc();
    Maybe<Long> add(Expense expenseModel);
    Maybe<Expense> findById(Long id);
}
