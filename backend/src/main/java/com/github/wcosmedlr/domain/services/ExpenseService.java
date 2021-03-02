package com.github.wcosmedlr.domain.services;

import com.github.wcosmedlr.domain.models.Expense;
import com.github.wcosmedlr.ports.primary.services.ExpenseServiceI;
import com.github.wcosmedlr.ports.secondary.repositories.ExpenseRepositoryI;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ExpenseService implements ExpenseServiceI {

    private ExpenseRepositoryI expenseRepository;

    public ExpenseService() {}

    @Inject
    public ExpenseService(ExpenseRepositoryI expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Single<List<Expense>> findAllOrderByTimeStampDesc() {
        return expenseRepository.findAllOrderByTimeStampDesc();
    }

    @Override
    public Maybe<Long> add(Expense expense) {
        return expenseRepository.add(expense);
    }

    @Override
    public Maybe<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }
}
