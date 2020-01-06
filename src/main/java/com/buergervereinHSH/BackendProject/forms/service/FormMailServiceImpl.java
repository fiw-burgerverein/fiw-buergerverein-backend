package com.buergervereinHSH.BackendProject.forms.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class FormMailServiceImpl implements FormMailService {

    @Autowired
    public JavaMailSender emailSender;


    @Override
    public void sendMailWithAttachment(String to, String subject, String text, String pathToAttachment) throws MailException, MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        //Filename anpassen
        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Antrag.pdf", file);

       /* ClassPathResource cpr = new ClassPathResource(pathToAttachment);   //
        helper.addAttachment(cpr.getFilename(), cpr);*/

        emailSender.send(message);
    }
}
