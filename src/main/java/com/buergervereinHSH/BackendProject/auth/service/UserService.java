package com.buergervereinHSH.BackendProject.auth.service;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.LoginDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.SignUpDto;
import com.buergervereinHSH.BackendProject.auth.model.User;

public interface UserService {

    ApiResponse signUp(SignUpDto signUpDto);

    ApiResponse login(LoginDto loginDto);

    void createVerificationTokenForUser(User user, String token);

    //ApiResponse confirmRegistration(

   //public VerificationToken generateNewVerificationToken(final String existingVerificationToken); //neuen Token generieren




}
