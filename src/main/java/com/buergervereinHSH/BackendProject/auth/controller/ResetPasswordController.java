package com.buergervereinHSH.BackendProject.auth.controller;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.ForgotPasswordDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.ResetPasswordDto;
import com.buergervereinHSH.BackendProject.auth.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class ResetPasswordController {
    @Autowired
    private ResetPasswordService resetPasswordService;

    @PostMapping("/forgot")
    public ApiResponse sendResetToken(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto) {
        return resetPasswordService.sendResetToken(forgotPasswordDto);
    }

    @GetMapping("/reset-password")
    public ApiResponse checkResetToken(@RequestParam("token") String resetToken) {
        return resetPasswordService.checkResetToken(resetToken);
    }

    @PostMapping("reset-password/ok")
    public ApiResponse saveNewPassword(@RequestParam("token") String resetToken,
                                       @Valid @RequestBody ResetPasswordDto resetPasswordDto) {
        return resetPasswordService.saveNewPassword(resetToken, resetPasswordDto);
    }

//    altes code
//    @PostMapping("/check-token")
//    public ApiResponse checkResetToken(@RequestBody ResetPasswordDto resetPasswordDto) {
//        return resetPasswordService.checkResetToken(resetPasswordDto);
//    }
//    @PostMapping("/check-token/ok")
//    public ApiResponse saveNewPassword(@RequestBody ResetPasswordDto resetPasswordDto){
//        return resetPasswordService.saveNewPassword(resetPasswordDto);
//    }

}
