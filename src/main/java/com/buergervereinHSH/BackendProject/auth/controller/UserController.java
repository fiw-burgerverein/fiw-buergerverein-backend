package com.buergervereinHSH.BackendProject.auth.controller;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.LoginDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.SignUpDto;
import com.buergervereinHSH.BackendProject.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registrieren")
    public ApiResponse signUp(@Valid @RequestBody SignUpDto signUpDto){ return userService.signUp(signUpDto); }

    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }

    @RequestMapping(value="/accountbestaetigung", method= {RequestMethod.GET, RequestMethod.POST})
    // @ResponseBody
    public ApiResponse confirmAccount(@RequestParam("token")String verificationToken){
        return userService.confirmAccount(verificationToken);
    }

}



