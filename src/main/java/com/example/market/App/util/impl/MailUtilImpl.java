package com.example.market.App.util.impl;

import com.example.market.App.util.MailUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.File;

public class MailUtilImpl implements MailUtil {

    private final static String SENDER = "market_bot@abv.bg";
    private final static String PASSWORD = "Roko123@";
    private final static String SUBJECT = "Confirmed order.";
    private final static String MESSAGE = "Thank you for ordering from Mr and Ms T store. Your order will be sent to ";
    private final static String LOGO_PATH = "D:\\Spring\\market\\src\\main\\resources\\Static\\order-confirmed-eps-vector_csp55001787.jpg";

    private final JavaMailSenderImpl mailSender;
    private final Logger logger;

    public MailUtilImpl(JavaMailSenderImpl mailSender, Logger logger) {
        this.mailSender = mailSender;
        this.logger = logger;
    }

    @Override
    @Transactional
    public void sentAutomaticEmailForConfirmedOrder(String receiver, String personAddress) {
        MimeMessage mailMessage = this.mailSender.createMimeMessage();

        try {

            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);

            this.mailSender.setUsername(SENDER);
            this.mailSender.setPassword(PASSWORD);

            helper.setFrom(SENDER);
            helper.setTo(receiver);
            helper.setSubject(SUBJECT);
            helper.setText(MESSAGE + " " + personAddress + ".");

            FileSystemResource file = new FileSystemResource(new File(LOGO_PATH));
            helper.addAttachment(LOGO_PATH, file);

            this.mailSender.send(mailMessage);
        }catch (Exception e){
            this.logger.log(Level.SEVERE, "Error while sending automatic email.", e);
        }
    }
}
