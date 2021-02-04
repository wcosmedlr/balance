package com.github.wcosmedlr.clients


import com.github.wcosmedlr.models.Transaction
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("/transactions/")
interface TransactionClient {

    @Get("/")
    Single<List<Transaction>> findAll();

}
