package com.github.wcosmedlr.clients


import com.github.wcosmedlr.models.Expense
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import io.reactivex.Maybe
import io.reactivex.Single

@Client("/expenses")
interface ExpenseClient {

    @Get("/")
    Single<List<Expense>> findAllOrderByTimeStamp()

    @Get("/{id}")
    Maybe<Expense> findById(Long id)

    @Post("/")
    Maybe<Long> add(Expense expense)

}
