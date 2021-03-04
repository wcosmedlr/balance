package com.github.wcosmedlr.adapters.primary.controllers;

import com.github.wcosmedlr.adapters.primary.controllers.models.BalanceControllerModel;
import com.github.wcosmedlr.domain.models.BalanceStatus;
import com.github.wcosmedlr.ports.primary.services.BalanceServiceI;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

import javax.inject.Inject;

@Controller("/balances")
public class BalanceController {

    private final BalanceServiceI balanceService;

    @Inject
    public BalanceController(BalanceServiceI balanceService) {
        this.balanceService = balanceService;
    }

    @Get("/")
    public Single<BalanceControllerModel> findAll() {
        BalanceStatus status = balanceService.findAllBalanceStatus().blockingGet();
        return Single.just(BalanceControllerModel.builder()
                .setBalanceMembers(status.getBalanceMembers())
                .setTransactions(status.calculateTransactions())
                .build());
    }

}
