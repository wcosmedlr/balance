package com.github.wcosmedlr.clients

import com.github.wcosmedlr.dto.Balance
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("/balances")
interface BalanceClient <T extends Balance>{

    @Get("/")
    Single<List<T>> findAll()

}
