package com.example.market.App.addInfoUtil.impl;

import com.example.market.App.dtos.AddressDtos.AddAddressDto;
import com.example.market.App.dtos.AddressDtos.AddCountryDto;
import com.example.market.App.dtos.AddressDtos.AddTownDto;
import com.example.market.App.dtos.AddressDtos.SetLivingTownDto;
import com.example.market.App.addInfoUtil.AddAddressInfo;

import java.io.BufferedReader;
import java.io.IOException;

public class AddAddressInfoImpl implements AddAddressInfo {

    private final BufferedReader reader;

    public AddAddressInfoImpl(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public SetLivingTownDto addLivingCountryAndTown() throws IOException {

        System.out.println("Enter your country: ");
        String country = this.reader.readLine();

        System.out.println("Enter your living town: ");
        String livingTown = this.reader.readLine();

        System.out.println("Enter your address: ");
        String address = this.reader.readLine();

        return new SetLivingTownDto(new AddCountryDto(country), new AddTownDto(livingTown), new AddAddressDto(address));
    }
}
