package com.github.wcosmedlr.clients

import com.github.wcosmedlr.models.Member
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import io.reactivex.Maybe

@Client("/members")
interface MemberClient {

    @Get("/{id}")
    Maybe<Member> findById(Long id);

    @Post("/")
    Maybe<Long> add(Member member);

}
