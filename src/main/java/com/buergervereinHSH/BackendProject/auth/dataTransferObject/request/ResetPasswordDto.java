package com.buergervereinHSH.BackendProject.auth.dataTransferObject.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResetPasswordDto {

//    private String resetToken;
    @NotBlank @NotNull @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    @Size(min=8, max=32, message="Bitte wählen Sie einen Passwort, der länger als 8 Symbole und kürzer als 32 Symbole ist.")
    private String password;
    @NotBlank @NotNull @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String passwordConfirm;

//    public String getResetToken() { return resetToken; }

    public String getPasswordConfirm() { return passwordConfirm; }
    public void setPasswordConfirm(String passwordConfirm) { this.passwordConfirm = passwordConfirm; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
