package com.buergervereinHSH.BackendProject.auth.service;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.ForgotPasswordDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.ResetPasswordDto;

public interface ResetPasswordService {

    ApiResponse sendResetToken (ForgotPasswordDto forgotPasswordDto);
//    ApiResponse checkResetToken (ResetPasswordDto resetPasswordDto);
    ApiResponse checkResetToken (String resetToken);
    ApiResponse saveNewPassword (ResetPasswordDto resetPasswordDto);
}
