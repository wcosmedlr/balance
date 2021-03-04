package com.github.wcosmedlr.adapters.primary.controllers;

import com.github.wcosmedlr.domain.models.Expense;
import com.github.wcosmedlr.ports.primary.services.ExpenseServiceI;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.inject.Inject;
import java.util.List;

@Controller("/expenses")
public class ExpenseController {

    private final ExpenseServiceI expenseService;

    @Inject
    public ExpenseController(final ExpenseServiceI expenseService) {
        this.expenseService = expenseService;
    }

    @Get("/")
    public Single<List<Expense>> findAllOrderByTimeStamp() {
        return expenseService.findAllOrderByTimeStampDesc();
    }

    @Get("/{id}")
    public Maybe<Expense> findById(Long id) {
        return expenseService.findById(id);
    }

    @Post("/")
    public Maybe<Long> add(Expense expense) {
        return expenseService.add(expense);
    }

}
