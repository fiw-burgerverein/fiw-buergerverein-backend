/*
package com.buergervereinHSH.BackendProject.auth.controller;

import com.buergervereinHSH.BackendProject.auth.dataAccessObject.RoleDao;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDao;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.LoginDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.SignUpDto;
import com.buergervereinHSH.BackendProject.auth.model.Role;
import com.buergervereinHSH.BackendProject.auth.model.RoleName;
import com.buergervereinHSH.BackendProject.auth.model.User;
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

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpRequest){
        if (userDao.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role){
                case "admin":
                    Role adminRole = roleDao.findByName(RoleName.ROLE_ADMIN);
                        //    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);
                    //if findbyname == null dann exception

                    break;

                default:
                    Role userRole = roleDao.findByName(RoleName.ROLE_USER);
                         //   .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userDao.save(user);

        String token = UUID.randomUUID().toString();
        userServiceImpl.createVerificationTokenForUser(user, token);


//vollständige URL muss noch geändert werden
        emailImpl.sendSimpleMessage(user.getEmail(), "Bestätigung Ihres Accounts bei der Stadtteilkoordination HSH Nord",
                "Herzlich Willkommen bei der Stadtteilkoordination HSH Nord! \n\n" +
                        "Um Ihre Email Adresse zu bestätigen und somit Ihren Account freizuschalten, bitte klicken Sie auf den folgenden Link: "
                        + "http://localhost:8080/accountbestaetigung?token="+token+" \n\nNach erfolgreicher Aktivierung Ihres Accounts haben Sie die " +
                        "Möglichkeit sich einzuloggen. " +
                        "\n\nViele Grüße, \nIhre Stadtteilkoordination Hohenschönhausen Nord");


        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }


}
*/
