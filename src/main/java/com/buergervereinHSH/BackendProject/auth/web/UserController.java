package com.buergervereinHSH.BackendProject.auth.web;

import com.buergervereinHSH.BackendProject.auth.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.LoginDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.SignUpDto;
import com.buergervereinHSH.BackendProject.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/registrieren")
    public ApiResponse signUp(@RequestBody SignUpDto signUpDto){ return userService.signUp(signUpDto); }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }
}



