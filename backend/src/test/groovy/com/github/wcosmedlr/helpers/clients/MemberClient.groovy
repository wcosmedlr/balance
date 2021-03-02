package com.github.wcosmedlr.helpers.clients


import com.github.wcosmedlr.domain.models.Member
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import io.reactivex.Maybe
import io.reactivex.Single

@Client("/members")
interface MemberClient <T extends Member, K extends Long>{

    @Get("/")
    Single<List<T>> findAll()

    @Get("/{id}")
    Maybe<T> findById(K id);

    @Post("/")
    Maybe<K> add(T member);

}
