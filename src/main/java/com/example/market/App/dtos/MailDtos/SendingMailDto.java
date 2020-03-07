package com.example.market.App.dtos.MailDtos;

public class SendingMailDto {

    private String sender;

    private String emailPassword;

    private String receiver;

    private String subject;

    private String message;

    public SendingMailDto() {
    }

    public SendingMailDto(String sender, String emailPassword, String receiver, String subject, String message) {
        this.sender = sender;
        this.emailPassword = emailPassword;
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
