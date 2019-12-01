package com.buergervereinHSH.BackendProject.auth.web;


import com.buergervereinHSH.BackendProject.auth.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.buergervereinHSH.BackendProject.auth.dataAccessObject.VerificationTokenRepository;
import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.auth.service.EmailSenderService;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDao;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @RequestMapping(value="/register", method=RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, User user)
    {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, User user)
    {

        User existingUser = userDao.findByEmailIdIgnoreCase(user.getEmail());
        if(existingUser != null)
        {
            modelAndView.addObject("message","This email already exists!");
            modelAndView.setViewName("error");
        }
        else
        {
            userDao.save(user);

            VerificationToken confirmationToken = new VerificationToken(user);

            verificationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("chand312902@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8082/confirm-account?token="+confirmationToken.getToken());

            emailSenderService.sendEmail(mailMessage);

            modelAndView.addObject("emailId", user.getEmail());

            modelAndView.setViewName("successfulRegisteration");
        }

        return modelAndView;
    }



    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        VerificationToken token = verificationTokenRepository.findByVerificationToken(confirmationToken);

        if(token != null)
        {
            User user = userDao.findByEmailIdIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userDao.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public VerificationTokenRepository getVerificationTokenRepository() {
        return verificationTokenRepository;
    }

    public void setVerificationTokenRepository(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public EmailSenderService getEmailSenderService() {
        return emailSenderService;
    }

    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

}


//Ksenia:
/*import com.buergervereinHSH.BackendProject.auth.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.LoginDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.SignUpDto;
import com.buergervereinHSH.BackendProject.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;*/
/*

@RestController
@RequestMapping("/")
public class UserController {


//Ksenia:
    @Autowired
    private UserService userService;

    @PostMapping("/registrieren")
    public ApiResponse signUp(@RequestBody SignUpDto signUpDto){ return userService.signUp(signUpDto); }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }
}

*/
