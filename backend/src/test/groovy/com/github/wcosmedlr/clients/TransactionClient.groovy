package com.github.wcosmedlr.clients

import com.github.wcosmedlr.dto.Expense
import com.github.wcosmedlr.dto.Transaction
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("/transactions")
interface TransactionClient <T extends Transaction>{

    @Get("/")
    Single<List<T>> findAll();

}
