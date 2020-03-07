package com.example.market.App.addInfoUtil;

import com.example.market.App.dtos.PersonDto.*;

import java.util.List;

public interface LoginAndSignInPeopleInfo {

     SignInDto getSignInPersonInfo();

     Object getLogInInfo(String command);

     LoginDto logInInfo();

     LoginAsEmployeeDto employeeLogInInfo();

     HireEmployeeDto getHireEmployeeInfo();

     ChangeAccountPasswordDto changeAccountPasswordInfo();
}
