package com.github.wcosmedlr.adapters.secondary.persistence.persistence.repositories;

import com.github.wcosmedlr.adapters.secondary.persistence.persistence.models.ExpenseEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<ExpenseEntity, Long> {

    List<ExpenseEntity> listOrderByTimeStampDesc();

}
