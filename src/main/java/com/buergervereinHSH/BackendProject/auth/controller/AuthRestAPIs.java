package com.buergervereinHSH.BackendProject.auth.controller;

import com.buergervereinHSH.BackendProject.auth.dataAccessObject.RoleDao;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDao;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.VerificationTokenDao;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.LoginDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.SignUpDto;
import com.buergervereinHSH.BackendProject.auth.exceptions.AccountNotActivatedException;
import com.buergervereinHSH.BackendProject.auth.exceptions.EmailMismatchException;
import com.buergervereinHSH.BackendProject.auth.exceptions.*;
import com.buergervereinHSH.BackendProject.auth.model.Role;
import com.buergervereinHSH.BackendProject.auth.model.RoleName;
import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.auth.model.VerificationToken;
import com.buergervereinHSH.BackendProject.auth.security.jwt.JwtProvider;
import com.buergervereinHSH.BackendProject.auth.service.EmailServiceImpl;
import com.buergervereinHSH.BackendProject.auth.service.UserServiceImpl;
import com.buergervereinHSH.BackendProject.message.response.JwtResponse;
import com.buergervereinHSH.BackendProject.message.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDao userDao;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    RoleDao roleDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    EmailServiceImpl emailImpl;

    @Autowired
    private VerificationTokenDao verificationTokenDao;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto){

        User user = userDao.findByEmail(loginDto.getEmail());

        if(user == null) { throw new NoUserFoundException(); }
        if(!user.isEnabled()) { throw new AccountNotActivatedException(); }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt, loginDto.getEmail(), userServiceImpl.getAuthorities(user)));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto){

        if(!signUpDto.getEmail().equals(signUpDto.getEmailConfirm())) {
            throw new EmailMismatchException();
        }
        if(!signUpDto.getPassword().equals(signUpDto.getPasswordConfirm())) {
            throw new PasswordMismatchException();
        }

        User oldUser;

        if ((oldUser = userDao.findByEmail(signUpDto.getEmail())) != null){

            if(oldUser.isEnabled()){
                throw new EmailAlreadyInUseException();
            }
            else {
                userDao.deleteById(oldUser.getUserId());
            }
        }

        // Creating user's account
        User user = new User(signUpDto.getEmail(),
                passwordEncoder.encode(signUpDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleDao.findByName(RoleName.ROLE_USER);
        roles.add(userRole);
        user.setRoles(roles);

        userDao.save(user);

        String token = UUID.randomUUID().toString();
        userServiceImpl.createVerificationTokenForUser(user, token);


        //vollständige URL muss noch geändert werden
        emailImpl.sendSimpleMessage(user.getEmail(), "Bestätigung Ihres Accounts bei der Stadtteilkoordination "
                        + "HSH Nord",
                "Herzlich Willkommen bei der Stadtteilkoordination HSH Nord! \n\n" +
                        "Um Ihre Email Adresse zu bestätigen und somit Ihren Account freizuschalten, bitte kopieren sie "
                        + "folgenden Link in ihren Browser: "
                        + "http://localhost:8080/accountbestaetigung?token="+token+" \n\nNach erfolgreicher Aktivierung "
                        + "Ihres Accounts haben Sie die " + "Möglichkeit sich einzuloggen. " +
                        "\n\nViele Grüße, \nIhre Stadtteilkoordination Hohenschönhausen Nord");


        return new ResponseEntity<>(new ResponseMessage("Ein Bestätigungslink wurde an die von Ihnen angebene Email " +
                "gesendet."), HttpStatus.OK);
    }

    @PostMapping("/accountConfirm")
    public ResponseEntity<?> confirmAccount(@RequestParam("token")String verificationToken) {
        VerificationToken token = verificationTokenDao.findByToken(verificationToken);

        if(token != null)
        {
            User user = token.getUser();
            user.setEnabled(true);
            userDao.save(user);
            return new ResponseEntity<>(new ResponseMessage("Sie haben Ihren Account erfolgreich freigeschalten und " +
                    "werden nun  weitergeleitet zum Login"), HttpStatus.OK);

        /*    return new ApiResponse(200, "Sie haben Ihren Account erfolgreich freigeschalten und " +
                    "werden nun  weitergeleitet zum Login", null) ; //weiterleitung zum login (return
            // "redirect:/login.html?lang=" + request.getLocale().getLanguage(); )*/
        }
        else
        {
            throw new VerificationTokenLinkNotValid();
            //return new ApiResponse(400,"Dieser Link ist nicht gültig", null);
        }
    }

}
