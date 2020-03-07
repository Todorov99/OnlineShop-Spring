package com.example.market.App.addInfoUtil.impl;

import com.example.market.App.dtos.PersonDto.*;
import com.example.market.App.addInfoUtil.LoginAndSignInPeopleInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginAndSignInPeopleInfoImpl implements LoginAndSignInPeopleInfo {

    private final Scanner scanner;

    @Autowired
    public LoginAndSignInPeopleInfoImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public SignInDto getSignInPersonInfo() {
        List<String> info = personInfo();

        return new SignInDto(info.get(0), info.get(1),
                info.get(2), new PersonNamesDto(info.get(3), info.get(4)));
    }

    @Override
    public Object getLogInInfo(String command) {
        List<String> info = new ArrayList<>();

        if(command.equals("log in")) {
            return logInInfo();
        }
        else if (command.equals("log in as employee")){
           return employeeLogInInfo();
        }

        return info;
    }


    private List<String> personInfo() {

        List<String> info = new ArrayList<>();

        System.out.println("Enter username: ");
        info.add(this.scanner.next());

        System.out.println("Enter password: ");
        info.add(this.scanner.next());

        System.out.println("Enter email");
        info.add(this.scanner.next());

        System.out.println("Enter first name: ");
        info.add(this.scanner.next());

        System.out.println("Enter last name: ");
        info.add(this.scanner.next());

        return info;
    }

    @Override
    public LoginDto logInInfo(){

        System.out.println("Enter username: ");
        String username = this.scanner.next();

        System.out.println("Enter password: ");
        String password = this.scanner.next();

        return new LoginDto(username, password);
    }

    @Override
    public LoginAsEmployeeDto employeeLogInInfo() {

        System.out.println("Enter email: ");
        String email = this.scanner.next();

        System.out.println("Enter password: ");
        String password = this.scanner.next();

        return new LoginAsEmployeeDto(email, password);
    }


    private List<String> hireEmployeeInfo() {
        List<String> info = new ArrayList<>();

        System.out.println("Enter first name: ");
        info.add(this.scanner.next());

        System.out.println("Enter second name: ");
        info.add(this.scanner.next());

        System.out.println("Enter email");
        info.add(this.scanner.next());

        System.out.println("Enter password: ");
        info.add(this.scanner.next());

        return info;
    }

   @Override
    public HireEmployeeDto getHireEmployeeInfo() {
        List<String> info = hireEmployeeInfo();

        return new HireEmployeeDto(new PersonNamesDto(info.get(0), info.get(1)), info.get(2), info.get(3));
    }

    @Override
    public ChangeAccountPasswordDto changeAccountPasswordInfo() {

        System.out.println("Enter your old password: ");
        String oldPassword = this.scanner.next();

        System.out.println("Enter your new password: ");
        String newPassword = this.scanner.next();

        System.out.println("Confirm your new password: ");
        String confirmationPassword = this.scanner.next();

        return new ChangeAccountPasswordDto(oldPassword, newPassword, confirmationPassword);
    }
}
