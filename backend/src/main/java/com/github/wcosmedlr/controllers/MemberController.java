package com.github.wcosmedlr.controllers;

import com.github.wcosmedlr.models.Member;
import com.github.wcosmedlr.services.MemberServiceI;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

@Controller("/members")
public class MemberController {

    private final MemberServiceI memberService;

    public MemberController(final MemberServiceI memberService) {
        this.memberService = memberService;
    }

    @Get("/")
    public Single<List<Member>> findAll() {
        return memberService.findAll();
    }

    @Get("/{id}")
    public Maybe<Member> findById(Long id) {
        return memberService.findById(id);
    }

    @Post("/")
    public Maybe<Long> add(Member member) {
        return memberService.add(member);
    }

}
