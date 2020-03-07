package com.example.market.App.services;


import com.example.market.App.dtos.MailDtos.SendingMailDto;
import com.example.market.App.dtos.PersonDto.*;
import com.example.market.App.dtos.AddressDtos.SetLivingTownDto;

import java.io.IOException;
import java.util.List;

public interface PersonService {

    String signInPerson(SignInDto signInDto);

    String login(LoginDto loginDto);

    String loginAsEmployee(LoginAsEmployeeDto loginAsEmployeeDto);

    String logOut();

    String personOptions();

    String hireEmployee(HireEmployeeDto hireEmployeeDto) throws IOException;

    String retireEmployee(RetireEmployeeDto retireEmployeeDto) throws IOException;

    List<ExportAllEmployeesDto> seeAllEmployees() throws IOException;

    String getLoggedPerson();

    String changeAccountPassword(ChangeAccountPasswordDto changeAccountPasswordDto);

    String sendEmail(SendingMailDto sendingMailDto);

    String setPersonAddress(SetLivingTownDto setLivingTownDto);

}
