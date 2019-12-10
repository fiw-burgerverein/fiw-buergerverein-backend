package com.buergervereinHSH.BackendProject.auth.dataTransferObject;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ForgotPasswordDto {
    @NotBlank @NotNull @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    @Email(message = "Bitte geben Sie eine gültige Email-Adresse ein.")
    private String email;

    public String getEmail() { return email; }
}
