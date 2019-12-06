package com.buergervereinHSH.BackendProject.auth.web;

import com.buergervereinHSH.BackendProject.auth.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.ForgotPasswordDto;
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

}
