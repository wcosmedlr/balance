package com.github.wcosmedlr.services;

import com.github.wcosmedlr.dto.Member;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

public interface MemberServiceI {
    Maybe<Long> add(Member member);
    Maybe<Member> findById(Long id);
    Single<List<Member>> findAll();
}
