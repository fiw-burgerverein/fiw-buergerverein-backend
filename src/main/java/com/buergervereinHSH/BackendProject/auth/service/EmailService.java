package com.buergervereinHSH.BackendProject.auth.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendSimpleMessage(String to,
                           String subject,
                           String text);
}
