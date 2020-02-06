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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
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
        User user = new User();
/*
        if(!signUpDto.getEmail().equals(signUpDto.getEmailConfirm())) {
            throw new EmailMismatchException();
    }
        if(!signUpDto.getPassword().equals(signUpDto.getPasswordConfirm())) {
        throw new PasswordMismatchException();
    }*/

        User oldUser = userDao.findByEmail(signUpDto.getEmail());
        if ((oldUser != null)) {
            if (oldUser.isEnabled()) {
                throw new EmailAlreadyInUseException();
            } else {
                userDao.deleteById(oldUser.getUserId());
            }
        }

        BeanUtils.copyProperties(signUpDto, user);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(false);
        userDao.save(user);

        String token = UUID.randomUUID().toString();
        createVerificationTokenForUser(user, token);  //Erstellen&Speichern eines VerificationTokens-Objekts&Zuordnung zu User
      
        return new ApiResponse(200, "Ein Bestätigungslink wurde an die von Ihnen angebene Email gesendet.", user);
        //vollständige URL muss noch geändert werden
        emailImpl.sendSimpleMessage(user.getEmail(), "Bestätigung Ihres Accounts bei der Stadtteilkoordination HSH Nord",
                "Herzlich Willkommen bei der Stadtteilkoordination HSH Nord! \n\n" +
                "Um Ihre Email Adresse zu bestätigen und somit Ihren Account freizuschalten, bitte klicken Sie auf den folgenden Link: "
                + "http://localhost:4200/registrieren/accountbestaetigung?token="+token+" \n\nNach erfolgreicher Aktivierung Ihres Accounts haben Sie die Möglichkeit sich einzuloggen. " +
                "\n\nViele Grüße, \nIhre Stadtteilkoordination Hohenschönhausen Nord");
    }

    @Override
    public ApiResponse login(LoginDto loginDto) {

        User user = userDao.findByEmail(loginDto.getEmail());

        if (user == null) {
            throw new NoUserFoundException();
        }
        if (!user.isEnabled()) {
            throw new AccountNotActivatedException();
        }
        if (!encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }

        return new ApiResponse(200, "Sie sind jetzt eingeloggt!", null);

    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        VerificationToken myToken = new VerificationToken(user, token);
        verificationTokenDao.save(myToken);
    }


/*    @Override
    public ApiResponse confirmAccount(String verificationToken) {
        VerificationToken token = verificationTokenDao.findByToken(verificationToken);

        if(token != null)
        {
            User user = token.getUser();
            user.setEnabled(true);
            userDao.save(user);
            return new ApiResponse(200, "Sie haben Ihren Account erfolgreich freigeschalten und " +
                    "werden nun  weitergeleitet zum Login", null) ; //weiterleitung zum login (return "redirect:/login.html?lang=" + request.getLocale().getLanguage(); )
        }
        else
        {
            throw new InvalidLinkException();
        }
    }*/

    @Override
    public Set getAuthorities(User user) {
        Set authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }


   /* @Override   //alten Token überschreiben
    public VerificationToken generateNewVerificationToken(String existingVerificationToken) {
        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID()
                .toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }*/

}
