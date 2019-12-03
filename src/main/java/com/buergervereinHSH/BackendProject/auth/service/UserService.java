package com.buergervereinHSH.BackendProject.auth.service;

import com.buergervereinHSH.BackendProject.auth.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.LoginDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.SignUpDto;
import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.auth.model.VerificationToken;

public interface UserService {

    ApiResponse signUp(SignUpDto signUpDto);

    ApiResponse login(LoginDto loginDto);

    void createVerificationTokenForUser(User user, String token);

    //ApiResponse confirmRegistration(

   //public VerificationToken generateNewVerificationToken(final String existingVerificationToken); //neuen Token generieren




}
