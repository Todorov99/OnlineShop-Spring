package com.example.market.App.controllers;

import com.example.market.App.services.PersonService;
import com.example.market.App.addInfoUtil.AddAddressInfo;
import com.example.market.App.addInfoUtil.AddMailInfo;
import com.example.market.App.addInfoUtil.LoginAndSignInPeopleInfo;
import com.example.market.App.addInfoUtil.RetirePeopleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PersonController {

    private final PersonService personService;
    private final RetirePeopleInfo retirePeopleInfo;
    private final LoginAndSignInPeopleInfo loginAndSignInPeopleInfo;
    private final AddMailInfo addMailInfo;
    private final AddAddressInfo addAddressInfo;

    @Autowired
    public PersonController(PersonService personService, RetirePeopleInfo retirePeopleInfo, LoginAndSignInPeopleInfo loginAndSignInPeopleInfo, AddMailInfo addMailInfo, AddAddressInfo addAddressInfo) {
        this.personService = personService;
        this.retirePeopleInfo = retirePeopleInfo;
        this.loginAndSignInPeopleInfo = loginAndSignInPeopleInfo;
        this.addMailInfo = addMailInfo;
        this.addAddressInfo = addAddressInfo;
    }

    private void printing(String s) {
        System.out.println(s);
    }

    public void retireEmployee() throws IOException {

        printing(this.personService.retireEmployee(this.retirePeopleInfo.retirePersonInfo()));
    }

    public void logout() {
        printing(this.personService.logOut());
    }

    public void signIn() {
        printing(this.personService.signInPerson(this.loginAndSignInPeopleInfo.getSignInPersonInfo()));
    }

    public void seeEmployee() throws IOException {
        this.personService.seeAllEmployees().forEach(System.out::println);
    }

    public void personOptions(){
        printing(this.personService.personOptions());
    }

    public void hireEmployee() throws IOException {
        printing(this.personService.hireEmployee(this.loginAndSignInPeopleInfo.getHireEmployeeInfo()));
    }

    public void loginAsEmployee() {
        printing(this.personService.loginAsEmployee(this.loginAndSignInPeopleInfo.employeeLogInInfo()));
        printing(this.personService.personOptions());
    }

    public void login() {
        printing(this.personService.login(this.loginAndSignInPeopleInfo.logInInfo()));
        printing(this.personService.personOptions());
    }

    public void changeAccountPassword(){
        printing(this.personService.changeAccountPassword(this.loginAndSignInPeopleInfo.changeAccountPasswordInfo()));
    }

    public void sendMails() throws IOException {
        printing(this.personService.sendEmail(this.addMailInfo.mailInfo()));
    }

    public void setTown() throws IOException {
        printing(this.personService.setPersonAddress(this.addAddressInfo.addLivingCountryAndTown()));
    }
}
