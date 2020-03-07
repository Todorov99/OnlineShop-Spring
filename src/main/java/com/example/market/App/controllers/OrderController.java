package com.example.market.App.controllers;

import com.example.market.App.services.OrderService;
import com.example.market.App.dtos.OrdersDto.AllSoldProductsDto;
import com.example.market.App.addInfoUtil.AddOrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderController {

    private final OrderService orderService;
    private final AddOrderInfo addOrderInfo;

    @Autowired
    public OrderController(OrderService orderService, AddOrderInfo addOrderInfo) {
        this.orderService = orderService;
        this.addOrderInfo = addOrderInfo;
    }

    public void seeSoldProducts() throws IOException {
        AllSoldProductsDto[] soldProducts = this.addOrderInfo.allSoldProductsInfo();

        System.out.println(String.format("%15s %5s %15s %10s %25s %20s %15s", "Category", "|", "Brand", "|", "Model", "|", "Quantity" ));
        System.out.println(String.format("%s", "--------------------------------------------------------------------------------------------------------------------------------------"));

        for (AllSoldProductsDto soldProduct : soldProducts) {
            System.out.println(soldProduct);
        }
    }

    public void seeTurnoverForCurrentPeriod() {
        System.out.println(this.orderService.seeTurnoverForCurrentPeriod(this.addOrderInfo.getDatesForTurnover()));
    }

    public void seeTurnover() {
        System.out.println(this.orderService.seeTurnover());
    }
}
