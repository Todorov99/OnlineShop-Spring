package com.example.market.App.services.Impl;

import com.example.market.App.services.PersonService;
import com.example.market.App.consoleInterface.ConsoleInterface;
import com.example.market.App.domain.entities.People.*;
import com.example.market.App.dtos.MailDtos.SendingMailDto;
import com.example.market.App.dtos.PersonDto.*;
import com.example.market.App.dtos.AddressDtos.SetLivingTownDto;
import com.example.market.App.repositories.AddressRepository;
import com.example.market.App.repositories.CountriesRepository;
import com.example.market.App.repositories.PersonRepository;
import com.example.market.App.repositories.TownsRepository;
import com.example.market.App.util.FileUtil;
import com.example.market.App.util.ValidatorUtil;
import com.example.market.App.util.impl.IsoUtilImpl;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class PersonServiceImpl implements PersonService {

    private final static String EMPLOYEES_JSON_FILE = "D:\\Spring\\market\\src\\main\\resources\\Json_Files\\employees.json";
    private final static String RETIRED_JSON_FILE = "D:\\Spring\\market\\src\\main\\resources\\Json_Files\\retiredEmployees.json";

    private final ModelMapper modelMapper;
    private final JavaMailSenderImpl mailSender;
    private final PersonRepository personRepository;
    private final CountriesRepository countriesRepository;
    private final TownsRepository townsRepository;
    private final AddressRepository addressRepository;
    private final ValidatorUtil validatorUtil;
    private final ConsoleInterface consoleInterface;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final IsoUtilImpl isoUtil;

    private String loggedPerson;

    @Autowired
    public PersonServiceImpl(ModelMapper modelMapper, JavaMailSenderImpl mailSender, PersonRepository personRepository, CountriesRepository countriesRepository, TownsRepository townsRepository, AddressRepository addressRepository, ValidatorUtil validatorUtil, ConsoleInterface consoleInterface, FileUtil fileUtil, Gson gson, IsoUtilImpl isoUtil) {
        this.modelMapper = modelMapper;
        this.mailSender = mailSender;
        this.personRepository = personRepository;
        this.countriesRepository = countriesRepository;
        this.townsRepository = townsRepository;
        this.addressRepository = addressRepository;
        this.validatorUtil = validatorUtil;
        this.consoleInterface = consoleInterface;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.isoUtil = isoUtil;
        this.loggedPerson = "";
    }

    @Override
    public String signInPerson(SignInDto signInDto) {

        StringBuilder sb = new StringBuilder();

        if (this.validatorUtil.isValidDto(signInDto) instanceof String){
            return sb.append(this.validatorUtil.isValidDto(signInDto)).toString();
        }

        Person person = this.modelMapper.map(signInDto, Person.class);


        Person dbPerson = this.personRepository.findByUsername(signInDto.getUsername()).orElse(null);

        if(dbPerson != null){
            return sb.append("Unavailable username!").toString();
        }

        Person dbPersonByMail = this.personRepository.findByEmail(signInDto.getEmail()).orElse(null);

        if(dbPersonByMail != null){
            return sb.append("Unavailable email!").toString();
        }

        if (this.personRepository.count() == 0) {
                person.setTypes(PersonTypes.ADMIN);
            } else {
                person.setTypes(PersonTypes.CLIENT);
            }


        sb.append(String.format("%s welcome to our store", person.getNames().getFirstName()));
            this.personRepository.saveAndFlush(person);

        return sb.toString();
    }

    @Override
    public String login(LoginDto loginDto) {

        Person person = this.personRepository.findByUsername(loginDto.getUsername()).orElse(null);

        return checkForCorrectData(person, loginDto.getPassword());
    }

    @Override
    public String loginAsEmployee(LoginAsEmployeeDto loginAsEmployeeDto) {

        Person person = this.personRepository.findByEmail(loginAsEmployeeDto.getEmail()).orElse(null);

        return checkForCorrectData(person, loginAsEmployeeDto.getPassword());
    }


    @Override
    public String logOut() {

        StringBuilder sb = new StringBuilder();

        if(this.loggedPerson.equals("")){
            return "There is no logged in user";
        }

        sb.append(String.format("Thank you for shopping in our store %s.", this.loggedPerson)).append(System.lineSeparator());
        this.loggedPerson = "";

        return sb.toString();
    }

    @Override
    public String personOptions() {

        StringBuilder sb = new StringBuilder();

        Person loggedPerson;

        if (this.loggedPerson.contains("@")){
            loggedPerson = this.personRepository.findByEmail(this.loggedPerson).orElse(null);
        }else {
            loggedPerson = this.personRepository.findByUsername(this.loggedPerson).orElse(null);
        }

        if(loggedPerson == null){
            return "";
        }

        switch (loggedPerson.getTypes()){

            case ADMIN:

            case EMPLOYEE:
                sb.append(this.consoleInterface.adminOptions());
                break;

            case CLIENT:
                this.consoleInterface.clientOptions();
                break;
        }

        return sb.toString();
    }


    @Override
    public String hireEmployee(HireEmployeeDto hireEmployeeDto) throws IOException {

        StringBuilder sb = new StringBuilder();

        Person loggedPerson = this.personRepository.findByUsername(this.loggedPerson).orElse(null);

        if(checkForLoggedInAdmin(loggedPerson).length() != 0){
            return checkForLoggedInAdmin(loggedPerson);
        }


        if(this.validatorUtil.isValidDto(hireEmployeeDto) instanceof String){
            return sb.append(this.validatorUtil.isValidDto(hireEmployeeDto)).toString();
        }

       Person employee = this.modelMapper.map(hireEmployeeDto, Person.class);

        Person dbPerson = this.personRepository.findByEmail(employee.getEmail()).orElse(null);

        if(dbPerson != null){
            return sb.append("Unavailable email").append(System.lineSeparator()).toString();
        }

        employee.setTypes(PersonTypes.EMPLOYEE);

        ExportAllEmployeesDto exportEmployee = this.modelMapper.map(hireEmployeeDto, ExportAllEmployeesDto.class);

        String json = this.gson.toJson(exportEmployee);
        this.fileUtil.saveJsonStringInFile(json, EMPLOYEES_JSON_FILE);


        this.personRepository.saveAndFlush(employee);
        sb.append(String.format("You successfully added %s %s as your employee.",
                employee.getNames().getFirstName(), employee.getNames().getLastName()));

        return sb.toString();
    }

    @Override
    public String retireEmployee(RetireEmployeeDto retireEmployeeDto) throws IOException {

        StringBuilder sb = new StringBuilder();


        Person loggedPerson = this.personRepository.findByUsername(this.loggedPerson).orElse(null);

        if(checkForLoggedInAdmin(loggedPerson).length() != 0){
            return checkForLoggedInAdmin(loggedPerson);
        }

        Person person = this.personRepository.findByFirstLastNameAndEmail(retireEmployeeDto.getFirstName(),
                retireEmployeeDto.getLastName(), retireEmployeeDto.getEmail()).orElse(null);

        if(person == null){
            return sb.append(String.format("There is no hired employee with names and email: %s %s %s", retireEmployeeDto.getFirstName(),
                    retireEmployeeDto.getLastName(), retireEmployeeDto.getEmail()))
                    .toString();
        }

        ExportRetiredPersonDto retiredPersonDto = this.modelMapper.map(person, ExportRetiredPersonDto.class);

        String json = this.gson.toJson(retiredPersonDto);

        this.fileUtil.saveJsonStringInFile(json, RETIRED_JSON_FILE);

        this.personRepository.deleteById(person.getId());
        sb.append("You successfully deleted your employee.").append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public List<ExportAllEmployeesDto> seeAllEmployees() {

        Person loggedPerson = this.personRepository.findByUsername(this.loggedPerson).orElse(null);

        if(checkForLoggedInAdmin(loggedPerson).length() != 0){
            System.out.println("You don't have admin permission for this option.");
            return new ArrayList<>();
        }

      List<Person> employees= this.personRepository.findByTypes(PersonTypes.EMPLOYEE);

      List<ExportAllEmployeesDto> exportAllEmployeesDtos = new ArrayList<>();

        for (Person employee : employees) {
            ExportAllEmployeesDto exportAllEmployeesDto = this.modelMapper.map(employee, ExportAllEmployeesDto.class);
            exportAllEmployeesDtos.add(exportAllEmployeesDto);
        }

      return exportAllEmployeesDtos;
    }

    @Override
    public String getLoggedPerson() {
        return this.loggedPerson;
    }

    @Override
    @Transactional
    public String changeAccountPassword(ChangeAccountPasswordDto changeAccountPasswordDto) {

        StringBuilder sb = new StringBuilder();

        Person loggedPerson;
        if(this.loggedPerson.contains("@")){
            loggedPerson = this.personRepository.findByEmail(this.loggedPerson).orElse(null);
        }else {
            loggedPerson = this.personRepository.findByUsername(this.loggedPerson).orElse(null);
        }

        if(loggedPerson == null){
            return sb.append("Sorry but there is no logged in person.").toString();
        }

        if(this.validatorUtil.isValidDto(changeAccountPasswordDto) instanceof String){
            return this.validatorUtil.isValidDto(changeAccountPasswordDto).toString();
        }

        if(!(loggedPerson.getPassword().equals(changeAccountPasswordDto.getOldPassword()))){
            return sb.append("Sorry but your old password is incorrect.").toString();
        }

        if (!(changeAccountPasswordDto.getNewPassword().equals(changeAccountPasswordDto.getConfirmNewPassword()))){
            return sb.append("Sorry but you have mismatch in confirmation password.").toString();
        }

        this.personRepository.changeUserPassword(changeAccountPasswordDto.getNewPassword(), loggedPerson.getId());
        sb.append("You successfully changed your password.");

        return sb.toString();
    }

    @Override
    public String sendEmail(SendingMailDto sendingMailDto) {

        StringBuilder sb = new StringBuilder();

        MimeMessage mailMessage = this.mailSender.createMimeMessage();

        try {

            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);

            this.mailSender.setUsername(sendingMailDto.getSender());
            this.mailSender.setPassword(sendingMailDto.getEmailPassword());

            helper.setFrom(sendingMailDto.getSender());
            helper.setTo(sendingMailDto.getReceiver());
            helper.setSubject(sendingMailDto.getSubject());
            helper.setText(sendingMailDto.getMessage());

            this.mailSender.send(mailMessage);

        }catch (Exception e){
            e.printStackTrace();
        }

        sb.append("Message was successfully sent!.");
        return sb.toString();
    }

    @Override
    @Transactional
    public String setPersonAddress(SetLivingTownDto setLivingTownDto) {

        StringBuilder sb = new StringBuilder();

        Person loggedPerson = this.personRepository.findByUsername(this.loggedPerson).orElse(null);

        if(loggedPerson == null){
            return sb.append("There is no logged in user!").toString();
        }

        if(!this.isoUtil.isValidCountry(setLivingTownDto.getCountry().getCountryName())){
            return sb.append("Invalid country name.").toString();
        }

        if(this.validatorUtil.isValidDto(setLivingTownDto.getTown()) instanceof String){
            return this.validatorUtil.isValidDto(setLivingTownDto.getTown()).toString();
        }


        Country country = this.countriesRepository.findByCountryName(setLivingTownDto.getCountry().getCountryName()).orElse(null);
        Towns town  = this.townsRepository.findByTownName(setLivingTownDto.getTown().getTownName()).orElse(null);
        Address address =this.addressRepository.findByAddress(setLivingTownDto.getAddress().getAddress()).orElse(null);

        if(country == null && town == null && address == null){

            country = this.modelMapper.map(setLivingTownDto.getCountry(), Country.class);
            town = this.modelMapper.map(setLivingTownDto.getTown(), Towns.class);
            address = this.modelMapper.map(setLivingTownDto.getAddress(), Address.class);

            town.setCountry(country);
            address.setTowns(town);

            loggedPerson.setTown(town);
            this.countriesRepository.saveAndFlush(country);

        }

        if(town == null && address == null){

            town = this.modelMapper.map(setLivingTownDto.getTown(), Towns.class);
            address = this.modelMapper.map(setLivingTownDto.getAddress(), Address.class);

            town.setCountry(country);
            address.setTowns(town);

            loggedPerson.setTown(town);
            this.addressRepository.saveAndFlush(address);
        }

        if (address == null){
            address = this.modelMapper.map(setLivingTownDto.getAddress(), Address.class);

            address.setTowns(town);

            this.addressRepository.saveAndFlush(address);
        }

        loggedPerson.setTown(town);
        this.personRepository.saveAndFlush(loggedPerson);
        sb.append(String.format("You successfully set address: %s, %s, %s", setLivingTownDto.getTown().getTownName(),
               setLivingTownDto.getCountry().getCountryName(), setLivingTownDto.getAddress().getAddress()));

        return sb.toString();
    }

    private String checkForLoggedInAdmin(Person person){

        StringBuilder sb = new StringBuilder();

        if(person == null){
            return sb.append("There is no logged person. Please log in.").toString();
        }

        if(!(person.getTypes().equals(PersonTypes.ADMIN))){
            return sb.append("You don't have permission to admin options").toString();
        }

        return sb.toString();
    }


    private String checkForCorrectData(Person person, String password){

        StringBuilder sb = new StringBuilder();

        if(person == null){

            return sb.append("Incorrect data!").toString();
        }else {
            if(!person.getPassword().equals(password)){
                return sb.append("Incorrect password").toString();
            }


            if(person.getTypes().equals(PersonTypes.EMPLOYEE)){
                this.loggedPerson = person.getEmail();
            }else {
                this.loggedPerson = person.getUsername();
            }


            sb.append(String.format("Welcome to our store store %s %s",
                    person.getNames().getFirstName(), person.getNames().getLastName()));
        }

        return sb.toString();
    }

}
