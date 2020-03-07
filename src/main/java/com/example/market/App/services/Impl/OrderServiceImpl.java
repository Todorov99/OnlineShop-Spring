package com.example.market.App.services.Impl;

import com.example.market.App.services.OrderService;
import com.example.market.App.services.PersonService;
import com.example.market.App.domain.entities.People.Person;
import com.example.market.App.domain.entities.People.PersonTypes;
import com.example.market.App.dtos.OrdersDto.TurnOverForCurrentPeriodDto;
import com.example.market.App.repositories.OrdersRepository;
import com.example.market.App.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;
    private final PersonService personService;
    private final PersonRepository personRepository;

    @Autowired
    public OrderServiceImpl(OrdersRepository ordersRepository, PersonService personService, PersonRepository personRepository) {
        this.ordersRepository = ordersRepository;
        this.personService = personService;
        this.personRepository = personRepository;
    }

    @Override
    public String seeTurnover() {

        StringBuilder sb = new StringBuilder();

        if(checkForCorrectTypeOfLoggedInPerson(this.personService.getLoggedPerson()) instanceof String){
            return checkForCorrectTypeOfLoggedInPerson(this.personService.getLoggedPerson()).toString();
        }

        BigDecimal marketTurnover = this.ordersRepository.turnOver();

        sb.append(String.format("The turnover of Mr and Mrs T store is %s lv.", marketTurnover));

        return sb.toString();
    }

    @Override
    public String seeTurnoverForCurrentPeriod(TurnOverForCurrentPeriodDto turnOverForCurrentPeriodDto) {

        StringBuilder sb = new StringBuilder();

        if(checkForCorrectTypeOfLoggedInPerson(this.personService.getLoggedPerson()) instanceof String){
            return checkForCorrectTypeOfLoggedInPerson(this.personService.getLoggedPerson()).toString();
        }

        if (turnOverForCurrentPeriodDto == null){
            return sb.append("You've entered invalid type of date!").toString();
        }

        BigDecimal turnoverForCurrentPeriod = this.ordersRepository.turOverForCurrentPeriod(turnOverForCurrentPeriodDto.getFrom(),
                turnOverForCurrentPeriodDto.getTo()).orElse(null);

        if(turnoverForCurrentPeriod == null){
            return sb.append(String.format("There is no order is period %s - %s", turnOverForCurrentPeriodDto.getFrom(),
                   turnOverForCurrentPeriodDto.getTo())).toString();
        }

        sb.append(String.format("The turnover for the period [%s - %s] is %s",
                turnOverForCurrentPeriodDto.getFrom(), turnOverForCurrentPeriodDto.getTo(), turnoverForCurrentPeriod));

        return sb.toString();
    }

    private Object checkForCorrectTypeOfLoggedInPerson(String username){

        StringBuilder sb = new StringBuilder();

        Person loggedPerson = this.personRepository.findByUsername(username).orElse(null);

        if(loggedPerson == null){
            return sb.append("There is no logged in person.").append(System.lineSeparator()).toString();
        }

        if(loggedPerson.getTypes().equals(PersonTypes.CLIENT)){
            return sb.append("Invalid option").toString();
        }

        return loggedPerson;
    }
}
