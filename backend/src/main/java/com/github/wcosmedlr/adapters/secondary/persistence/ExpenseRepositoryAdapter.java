package com.github.wcosmedlr.adapters.secondary.persistence;

import com.github.wcosmedlr.adapters.secondary.persistence.models.ExpenseEntity;
import com.github.wcosmedlr.adapters.secondary.persistence.repositories.ExpenseRepository;
import com.github.wcosmedlr.domain.models.Expense;
import com.github.wcosmedlr.ports.secondary.repositories.ExpenseRepositoryI;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class ExpenseRepositoryAdapter implements ExpenseRepositoryI {
    private final ExpenseRepository expenseRepository;

    @Inject
    public ExpenseRepositoryAdapter(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Single<List<Expense>> findAllOrderByTimeStampDesc() {
        List<Expense> result = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(expenseRepository.listOrderByTimeStampDesc()
                        .iterator(), Spliterator.ORDERED), false)
                .map(entity -> entity.toServiceModel())
                .collect(Collectors.toList()
                );
        return Single.just(result);
    }

    @Override
    public Maybe<Long> add(Expense expense) {
        ExpenseEntity entity = new ExpenseEntity(expense);
        entity = expenseRepository.save(entity);
        return Maybe.just(entity.getId());
    }

    @Override
    public Maybe<Expense> findById(Long id) {
        ExpenseEntity entity = expenseRepository.findById(id)
                .orElse(null);
        return Maybe.just(entity.toServiceModel());
    }
}
