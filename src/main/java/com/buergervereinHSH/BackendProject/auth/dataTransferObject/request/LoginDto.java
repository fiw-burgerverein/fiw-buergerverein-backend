package com.buergervereinHSH.BackendProject.auth.dataTransferObject.request;

import com.buergervereinHSH.BackendProject.auth.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginDto {

    @NotBlank @NotNull @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    @Email(message = "Bitte geben Sie eine gültige Email-Adresse ein.")
    private String email;
    @NotBlank @NotNull @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String password;
    private Role role;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() { return role; }

}
