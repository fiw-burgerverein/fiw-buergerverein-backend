package com.buergervereinHSH.BackendProject.auth.service;

import com.buergervereinHSH.BackendProject.auth.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.ForgotPasswordDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.ResetPasswordDto;

public interface ResetPasswordService {

    ApiResponse sendResetToken (ForgotPasswordDto forgotPasswordDto);
    ApiResponse checkResetToken (ResetPasswordDto resetPasswordDto);
    ApiResponse saveNewPassword (ResetPasswordDto resetPasswordDto);
}
