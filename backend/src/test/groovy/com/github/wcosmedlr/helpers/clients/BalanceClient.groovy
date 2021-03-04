package com.github.wcosmedlr.helpers.clients

import com.github.wcosmedlr.adapters.primary.controllers.models.BalanceControllerModel
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("/balances")
interface BalanceClient {

    @Get("/")
    Single<BalanceControllerModel> findAll();

}
