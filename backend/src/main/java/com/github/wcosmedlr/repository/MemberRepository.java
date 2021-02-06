package com.github.wcosmedlr.repository;

import com.github.wcosmedlr.dao.MemberEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, Long> {
}
