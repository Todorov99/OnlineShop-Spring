package com.example.market.App.addInfoUtil.impl;

import com.example.market.App.dtos.OrdersDto.AllSoldProductsDto;
import com.example.market.App.dtos.OrdersDto.TurnOverForCurrentPeriodDto;
import com.example.market.App.addInfoUtil.AddOrderInfo;
import com.example.market.App.util.FileUtil;
import com.example.market.App.util.ValidatorUtil;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class AddOrderInfoImpl implements AddOrderInfo {

    private static final String SOLD_PRODUCTS_JSON_FILE = "D:\\Spring\\market\\src\\main\\resources\\Json_Files\\orders.json";

    private final Scanner scanner;
    private final FileUtil fileUtil;
    private final Gson gson;

    private final ValidatorUtil validatorUtil;
    @Autowired
    public AddOrderInfoImpl(Scanner scanner, FileUtil fileUtil, Gson gson, ValidatorUtil validatorUtil) {
        this.scanner = scanner;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public TurnOverForCurrentPeriodDto getDatesForTurnover() {

        System.out.println("Enter the first date in format (yyyy-MM-dd): ");
        String date = this.scanner.next();

        System.out.println("Enter the second date in format (yyyy-MM-dd): ");
        String date1 = this.scanner.next();

        if(!(this.validatorUtil.isValidDate(date)) || (!this.validatorUtil.isValidDate(date1))){
            return null;
        }

        LocalDate from = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDate to = LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return new TurnOverForCurrentPeriodDto(from, to);
    }

    @Override
    public AllSoldProductsDto[] allSoldProductsInfo() throws IOException {

        String content = this.fileUtil.fileContent(SOLD_PRODUCTS_JSON_FILE);

        StringReader reader = new StringReader(content);

        return this.gson.fromJson(new JsonReader(reader), AllSoldProductsDto[].class);
    }
}
