package com.github.wcosmedlr.services;

import com.github.wcosmedlr.dao.ExpenseEntity;
import com.github.wcosmedlr.dto.Expense;
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

    public Single<List<Expense>> findAllOrderByTimeStampDesc() {
        List<Expense> result = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(expenseRepository.listOrderByTimestampDesc()
                        .iterator(), Spliterator.ORDERED), false)
            .map(expenseEntity -> modelMapper.map(expenseEntity, Expense.class))
            .collect(Collectors.toList()
        );
        return Single.just(result);
    }

    @Override
    public Maybe<Long> add(Expense expense) {
        ExpenseEntity expenseEntity = modelMapper.map(expense, ExpenseEntity.class);
        expenseEntity = expenseRepository.save(expenseEntity);
        return Maybe.just(expenseEntity.getId());
    }

    @Override
    public Maybe<Expense> findById(Long id) {
        ExpenseEntity expenseEntity = expenseRepository.findById(id).orElse(null);
        return Maybe.just(modelMapper.map(expenseEntity, Expense.class));
    }
}
