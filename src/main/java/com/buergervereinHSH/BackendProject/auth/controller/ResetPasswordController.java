package com.buergervereinHSH.BackendProject.auth.controller;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.ForgotPasswordDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.ResetPasswordDto;
import com.buergervereinHSH.BackendProject.auth.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")   //oder root?
public class ResetPasswordController {
    @Autowired
    private ResetPasswordService resetPasswordService;

    @PostMapping("/forgot")
    public ApiResponse sendResetToken(@RequestBody ForgotPasswordDto forgotPasswordDto) {
        return resetPasswordService.sendResetToken(forgotPasswordDto);
    }

    @RequestMapping(value = "/reset-password?token={resetToken}", method = {RequestMethod.GET, RequestMethod.POST})
    public ApiResponse checkResetToken(@PathVariable("resetToken") @RequestParam String resetToken) {
        return resetPasswordService.checkResetToken(resetToken);
    }

    @PostMapping("reset-password/ok?token={resetToken}")
    public ApiResponse saveNewPassword(@PathVariable("resetToken") String resetToken, @RequestBody ResetPasswordDto resetPasswordDto) {
        return resetPasswordService.saveNewPassword(resetToken, resetPasswordDto);
    }


//  auch altes code
//    @PostMapping("reset-password/ok")
//    public ApiResponse saveNewPassword(@RequestParam("resetToken") String resetToken, @RequestBody ResetPasswordDto resetPasswordDto) {
//        return resetPasswordService.saveNewPassword(resetToken, resetPasswordDto);
//    }

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
