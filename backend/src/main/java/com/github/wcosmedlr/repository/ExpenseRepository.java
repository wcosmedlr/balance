package com.github.wcosmedlr.repository;

import com.github.wcosmedlr.dao.ExpenseEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<ExpenseEntity, Long> {

    List<ExpenseEntity> listOrderByTimestampDesc();

}
