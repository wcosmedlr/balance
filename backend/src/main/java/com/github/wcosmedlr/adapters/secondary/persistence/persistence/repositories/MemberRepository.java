package com.github.wcosmedlr.adapters.secondary.persistence.persistence.repositories;

import com.github.wcosmedlr.adapters.secondary.persistence.persistence.models.MemberEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, Long> {
}
