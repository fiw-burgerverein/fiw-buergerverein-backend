package com.buergervereinHSH.BackendProject.auth.service;

import com.buergervereinHSH.BackendProject.auth.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.ForgotPasswordDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.ResetTokenDto;

public interface ResetPasswordService {

    ApiResponse sendResetToken (ForgotPasswordDto forgotPasswordDto);
    ApiResponse checkResetToken (ResetTokenDto resetTokenDto);
}
