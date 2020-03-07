package com.example.market.App.addInfoUtil;

import com.example.market.App.dtos.OrdersDto.AllSoldProductsDto;
import com.example.market.App.dtos.OrdersDto.TurnOverForCurrentPeriodDto;

import java.io.IOException;

public interface AddOrderInfo {

    TurnOverForCurrentPeriodDto getDatesForTurnover();

    AllSoldProductsDto[] allSoldProductsInfo() throws IOException;
}
