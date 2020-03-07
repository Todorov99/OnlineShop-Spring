package com.example.market.App.addInfoUtil.impl;

import com.example.market.App.dtos.MailDtos.SendingMailDto;
import com.example.market.App.addInfoUtil.AddMailInfo;

import java.io.BufferedReader;
import java.io.IOException;

public class AddMailInfoImpl implements AddMailInfo {

    private final static String SEND = "send";
    private final BufferedReader reader;

    public AddMailInfoImpl(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public SendingMailDto mailInfo() throws IOException {

        StringBuilder sb = new StringBuilder();

        System.out.println("Enter your email: ");
        String senderEmail = this.reader.readLine();

        System.out.println("Enter your email password: ");
        String password = this.reader.readLine();

        System.out.println("Enter your receiver email: ");
        String receiverEmail = this.reader.readLine();

        System.out.println("Enter the subject of your email: ");
        String subject = this.reader.readLine();

        System.out.println("Enter your message. When you are reade write (send).");
        String text = this.reader.readLine();

        return new SendingMailDto(senderEmail, password, receiverEmail, subject, text);
    }
}
