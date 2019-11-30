//package com.buergervereinHSH.BackendProject.auth.web;
//
//import org.springframework.context.ApplicationEventPublisher;
//
//
//import java.util.Calendar;
//import java.util.Locale;
//import java.util.UUID;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//import org.baeldung.persistence.model.PasswordResetToken;
//import org.baeldung.persistence.model.User;
//import org.baeldung.persistence.model.VerificationToken;
//import org.baeldung.registration.OnRegistrationCompleteEvent;
//import org.baeldung.service.IUserService;
//import org.baeldung.web.dto.UserDto;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.MessageSource;
//import org.springframework.core.env.Environment;
//import org.springframework.mail.MailAuthenticationException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//
//public class RegistrationController {
//    @Autowired
//    ApplicationEventPublisher eventPublisher
//
//    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
//    public ModelAndView registerUserAccount(
//            @ModelAttribute("user") @Valid UserDto accountDto,
//            BindingResult result,
//            WebRequest request,
//            Errors errors) {
//
//        if (result.hasErrors()) {
//            return new ModelAndView("registration", "user", accountDto);
//        }
//
//        User registered = createUserAccount(accountDto);
//        if (registered == null) {
//            result.rejectValue("email", "message.regError");
//        }
//        try {
//            String appUrl = request.getContextPath();
//            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
//                    (registered, request.getLocale(), appUrl));
//        } catch (Exception me) {
//            return new ModelAndView("emailError", "user", accountDto);
//        }
//        return new ModelAndView("successRegister", "user", accountDto);
//    }
//}
