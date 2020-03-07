package com.example.market.App.services;

import com.example.market.App.dtos.OrdersDto.TurnOverForCurrentPeriodDto;

public interface OrderService {

        String seeTurnover();

        String seeTurnoverForCurrentPeriod(TurnOverForCurrentPeriodDto turnOverForCurrentPeriodDto);

}
