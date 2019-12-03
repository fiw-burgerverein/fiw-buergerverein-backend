package com.buergervereinHSH.BackendProject.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailServiceImpl implements EmailService  {

    @Autowired
    public JavaMailSender emailSender;

  //  public String tokenMessageEmail; ?

    public void sendSimpleMessage(String to, String subject, String text) {  //Hier Subject und text am besten schon festlegen?
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            this.emailSender.send(message);

        } catch (MailException exception) {
            exception.printStackTrace();  //displays error message in the console; where we are getting the exception in the source code
        }
    }
}
