package com.github.wcosmedlr.controllers;

import com.github.wcosmedlr.dto.Balance;
import com.github.wcosmedlr.services.BalanceServiceI;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

import javax.inject.Inject;
import java.util.List;

@Controller("/balances")
public class BalanceController {

    private final BalanceServiceI balanceService;

    @Inject
    public BalanceController(BalanceServiceI balanceService) {
        this.balanceService = balanceService;
    }

    @Get("/")
    public Single<List<Balance>> findAll() {
        return balanceService.findAll();
    }

}
