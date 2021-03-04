package com.github.wcosmedlr.ports.primary.services;

import com.github.wcosmedlr.domain.models.Member;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

public interface MemberServiceI {
    Maybe<Long> add(Member member);
    Maybe<Member> findById(Long id);
    Single<List<Member>> findAll();
}
