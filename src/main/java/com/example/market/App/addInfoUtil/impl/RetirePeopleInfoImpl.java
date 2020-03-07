package com.example.market.App.addInfoUtil.impl;

import com.example.market.App.dtos.PersonDto.RetireEmployeeDto;
import com.example.market.App.addInfoUtil.RetirePeopleInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class RetirePeopleInfoImpl implements RetirePeopleInfo {

    private final Scanner scanner;

    @Autowired
    public RetirePeopleInfoImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public RetireEmployeeDto retirePersonInfo() {

        System.out.println("Enter employee's first name: ");
        String firstName = this.scanner.nextLine();

        System.out.println("Enter employee's last name: ");
        String lastName = this.scanner.nextLine();

        System.out.println("Enter employee's email: ");
        String email = this.scanner.nextLine();

        return new RetireEmployeeDto(firstName, lastName, email);
    }

}
