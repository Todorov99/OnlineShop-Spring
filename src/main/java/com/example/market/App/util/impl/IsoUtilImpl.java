package com.example.market.App.util.impl;

import com.example.market.App.util.IsoUtil;
import com.neovisionaries.i18n.CountryCode;

import java.util.*;

public class IsoUtilImpl implements IsoUtil {

    private static final Set<String> ISO_COUNTRIES = new HashSet<>(Arrays.asList(Locale.getISOCountries()));

    @Override
    public boolean isValidCountry(String country) {

        List<CountryCode> codes = CountryCode.findByName(country);

        String code = "";

        for (CountryCode countryCode : codes) {
            if (countryCode.getName().equals(country)){
                code = countryCode.name();
            }
        }

        return ISO_COUNTRIES.contains(code);
    }

}
