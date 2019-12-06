package com.buergervereinHSH.BackendProject.auth.service;

import com.buergervereinHSH.BackendProject.auth.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.ForgotPasswordDto;

public interface ResetPasswordService {
    ApiResponse sendResetToken (ForgotPasswordDto forgotPasswordDto);
}
