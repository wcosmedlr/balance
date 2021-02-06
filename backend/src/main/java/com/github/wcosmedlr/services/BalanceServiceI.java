package com.github.wcosmedlr.services;

import com.github.wcosmedlr.dto.Balance;
import io.reactivex.Single;

import java.util.List;

public interface BalanceServiceI {
    Single<List<Balance>> findAll();
}
