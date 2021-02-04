package com.github.wcosmedlr.services;

import com.github.wcosmedlr.dao.Expense;
import com.github.wcosmedlr.repository.ExpenseRepository;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class ExpenseService implements ExpenseServiceI {

    private final ModelMapper modelMapper;
    private final ExpenseRepository expenseRepository;


    @Inject
    public ExpenseService(ModelMapper modelMapper, ExpenseRepository expenseRepository) {
        this.modelMapper = modelMapper;
        this.expenseRepository = expenseRepository;
    }

    public Single<List<com.github.wcosmedlr.models.Expense>> findAllOrderByTimeStampDesc() {
        List<com.github.wcosmedlr.models.Expense> result = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(expenseRepository.listOrderByTimeStampDesc()
                        .iterator(), Spliterator.ORDERED), false)
            .map(expense -> modelMapper.map(expense, com.github.wcosmedlr.models.Expense.class))
            .collect(Collectors.toList()
        );
        return Single.just(result);
    }

    @Override
    public Maybe<Long> add(com.github.wcosmedlr.models.Expense expense) {
        Expense expenseEntity = modelMapper.map(expense, Expense.class);
        expenseEntity = expenseRepository.save(expenseEntity);
        return Maybe.just(expenseEntity.getId());
    }

    @Override
    public Maybe<com.github.wcosmedlr.models.Expense> findById(Long id) {
        Expense expense = expenseRepository.findById(id).orElse(null);
        return Maybe.just(modelMapper.map(expense, com.github.wcosmedlr.models.Expense.class));
    }
}
