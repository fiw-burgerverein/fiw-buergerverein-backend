package com.buergervereinHSH.BackendProject.forms.service;

import javax.mail.MessagingException;

public interface FormMailService {

    void sendMailWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException;
}
