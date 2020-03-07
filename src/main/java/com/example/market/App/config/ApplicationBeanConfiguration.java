package com.example.market.App.config;

import com.example.market.App.addInfoUtil.impl.*;
import com.example.market.App.consoleInterface.ConsoleInterface;
import com.example.market.App.util.impl.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ConsoleInterface consoleInterface(){
        return new ConsoleInterface();
    }

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public Validator validator(){
        return  Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidatorUtilImpl validatorUtilImpl(){
        return new ValidatorUtilImpl(validator());
    }

    @Bean
    public StringBuilder sb(){
        return new StringBuilder();
    }

    @Bean
    public LoginAndSignInPeopleInfoImpl loginAndSignInPeopleInfo(){
        return new LoginAndSignInPeopleInfoImpl(scanner());
    }

    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public FileUtilImpl fileUtil(){
        return new FileUtilImpl();
    }

    @Bean
    public RetirePeopleInfoImpl retirePeopleInfo(){
        return new RetirePeopleInfoImpl(scanner());
    }

    @Bean
    public AddProductInfoImpl addProductInfo(){
        return new AddProductInfoImpl(scanner(), modelMapper(), bufferedReader());
    }

    @Bean
    public AddOrderInfoImpl addOrderInfo(){
        return new AddOrderInfoImpl(scanner(), fileUtil(), gson(), validatorUtilImpl());
    }

    @Bean
    public BufferedReader bufferedReader(){
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setHost("smtp.abv.bg");
        mailSender.setPort(587);
        mailSender.setPort(465);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.enable", true);

        return mailSender;
    }

    @Bean
    public AddMailInfoImpl addMailInfo(){
        return new AddMailInfoImpl(bufferedReader());
    }

    @Bean
    public MailUtilImpl mailUtil(){
        return new MailUtilImpl(getJavaMailSender(), logger());
    }

    @Bean
    public AddAddressInfoImpl addAddressInfo(){
        return new AddAddressInfoImpl(bufferedReader());
    }

    @Bean
    public IsoUtilImpl isoUtil(){
        return new IsoUtilImpl();
    }

    @Bean
    public Logger logger(){
        return Logger.getAnonymousLogger();
    }

}
