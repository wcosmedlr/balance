package com.github.wcosmedlr.clients


import com.github.wcosmedlr.models.Balance
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("/balances")
interface BalanceClient {

    @Get("/")
    Single<List<Balance>> findAll()

}
