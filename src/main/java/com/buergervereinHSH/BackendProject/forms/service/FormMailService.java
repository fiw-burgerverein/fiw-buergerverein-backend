package com.buergervereinHSH.BackendProject.forms.service;

import org.springframework.core.io.ClassPathResource;

import javax.mail.MessagingException;

public interface FormMailService {

    void sendMailWithAttachment(String to, String subject, String text, String nameAttachment) throws MessagingException;
}
