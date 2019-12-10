package com.buergervereinHSH.BackendProject.auth.controller;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.ForgotPasswordDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.ResetPasswordDto;
import com.buergervereinHSH.BackendProject.auth.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")   //oder root?
public class ResetPasswordController {
    @Autowired
    private ResetPasswordService resetPasswordService;

    @PostMapping("/forgot")
    public ApiResponse sendResetToken(@RequestBody ForgotPasswordDto forgotPasswordDto) {
        return resetPasswordService.sendResetToken(forgotPasswordDto);
    }

    @PostMapping("/check-token")
    public ApiResponse checkResetToken(@RequestBody ResetPasswordDto resetPasswordDto) {
        return resetPasswordService.checkResetToken(resetPasswordDto);
    }

    @PostMapping("/check-token/ok")
    public ApiResponse saveNewPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        return resetPasswordService.saveNewPassword(resetPasswordDto);
    }

}
