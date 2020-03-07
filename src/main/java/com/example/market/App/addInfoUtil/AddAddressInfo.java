package com.example.market.App.addInfoUtil;

import com.example.market.App.dtos.AddressDtos.SetLivingTownDto;

import java.io.IOException;

public interface AddAddressInfo {

    SetLivingTownDto addLivingCountryAndTown() throws IOException;
}
