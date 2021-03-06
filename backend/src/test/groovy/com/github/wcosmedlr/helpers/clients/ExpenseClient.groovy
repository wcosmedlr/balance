package com.github.wcosmedlr.helpers.clients


import com.github.wcosmedlr.domain.models.Expense
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import io.reactivex.Maybe
import io.reactivex.Single

@Client("/expenses")
interface ExpenseClient <T extends Expense, K extends Long>{

    @Get("/")
    Single<List<T>> findAllOrderByTimeStamp()

    @Get("/{id}")
    Maybe<T> findById(K id)

    @Post("/")
    Maybe<K> add(T expense)

}
