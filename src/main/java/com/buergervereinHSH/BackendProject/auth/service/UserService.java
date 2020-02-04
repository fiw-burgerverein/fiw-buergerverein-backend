package com.buergervereinHSH.BackendProject.auth.service;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.LoginDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.SignUpDto;
import com.buergervereinHSH.BackendProject.auth.model.User;

import java.util.Set;

public interface UserService {

    ApiResponse signUp(SignUpDto signUpDto);

    ApiResponse login(LoginDto loginDto);

    void createVerificationTokenForUser(User user, String token);

   // ApiResponse confirmAccount(String verificationToken);

    Set getAuthorities(User user);

}
