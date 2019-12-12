package com.buergervereinHSH.BackendProject.auth.service;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDao;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.VerificationTokenDao;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.LoginDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.SignUpDto;
import com.buergervereinHSH.BackendProject.auth.exceptions.*;
import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.auth.model.VerificationToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private VerificationTokenDao verificationTokenDao;
    @Autowired
    private EmailServiceImpl emailImpl;

    @Override
    public ApiResponse signUp(SignUpDto signUpDto) {
        validateSignUp(signUpDto);
        User user = new User();

        if(!signUpDto.getEmail().equals(signUpDto.getEmailConfirm())) {
            throw new EmailMismatchException();
        }
        if(!signUpDto.getPassword().equals(signUpDto.getPasswordConfirm())) {
            throw new PasswordMismatchException();
        }
        if (userDao.findByEmail(signUpDto.getEmail()) != null && userDao.findByEmail(signUpDto.getEmail()).isEnabled()) { //nur wenn Benutzer bereits erfolgreich registriert
            throw new EmailAlreadyInUseException();
        }

        BeanUtils.copyProperties(signUpDto, user);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(false);
        userDao.save(user);

        String token = UUID.randomUUID().toString();
        createVerificationTokenForUser(user, token);  ////erstellen&speichern eines VerificationTokens-Objekts&zuordnung zu User

        //vollständige URL muss noch geändert werden
        emailImpl.sendSimpleMessage(user.getEmail(), "Bestätigung Ihres Accounts bei der Stadtteilkoordination HSH Nord",
                "Herzlich Willkommen bei der Stadtteilkoordination HSH Nord! \n\n" +
                "Um Ihre Email Adresse zu bestätigen und somit Ihren Account freizuschalten, bitte klicken Sie auf den folgenden Link: "
                + "http://localhost:8080/accountbestaetigung?token="+token+" \n\nNach erfolgreicher Aktivierung Ihres Accounts haben Sie die Möglichkeit sich einzuloggen. " +
                "\n\nViele Grüße, \nIhre Stadtteilkoordination Hohenschönhausen Nord");
        return new ApiResponse(200, "Ein Bestätigungslink wurde an die von Ihnen angebene Email gesendet.", user);
    }

    @Override
    public ApiResponse login(LoginDto loginDto) {

        User user = userDao.findByEmail(loginDto.getEmail());

        if(user == null) {
            throw new NoUserFoundException();
        }
        if(!user.isEnabled())
        {
            throw new AccountNotActivatedException();
        }
        if(!encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }

        return new ApiResponse(200, "Sie sind jetzt eingeloggt!", null) ;

    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        VerificationToken myToken = new VerificationToken(user, token);
        verificationTokenDao.save(myToken);
    }

    @Override
    public ApiResponse confirmAccount(String verificationToken) {
        VerificationToken token = verificationTokenDao.findByToken(verificationToken);

        if(token != null)
        {
            User user = token.getUser();
            user.setEnabled(true);
            userDao.save(user);
            return new ApiResponse(200, "Sie haben Ihren Account erfolgreich freigeschalten und " +
                    "werden nun  weitergeleitet zum Login", user) ; //weiterleitung zum login (return "redirect:/login.html?lang=" + request.getLocale().getLanguage(); )
        }
        else
        {
            return new ApiResponse(400,"Dieser Link ist nicht gültig", null);
        }



    }

    private void validateSignUp(SignUpDto signUpDto) {
    }

    /*@Override
    public void setEmailParam(User user, VerificationToken token) {

    }*/

   /* @Override   //alten Token überschreiben
    public VerificationToken generateNewVerificationToken(String existingVerificationToken) {
        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID()
                .toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }*/

}
