package com.github.wcosmedlr.controllers;

import com.github.wcosmedlr.models.Transaction;
import com.github.wcosmedlr.services.TransactionServiceI;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

import javax.inject.Inject;
import java.util.List;

@Controller("/transactions")
public class TransactionController {

    private final TransactionServiceI transactionService;

    @Inject
    public TransactionController(TransactionServiceI transactionService) {
        this.transactionService = transactionService;
    }

    @Get("/")
    public Single<List<Transaction>> findAll() {
        return transactionService.findAll();
    }

}
