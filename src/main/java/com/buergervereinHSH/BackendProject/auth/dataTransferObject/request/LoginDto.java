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
    //private Role role;  // meiner Meinung nach nicht

    public String getEmail() { return email; }
    public String getPassword() {
        return password;
    }


    /*

    @NotBlank
    @Size(min=3, max=60)
    private String email;

    @NotBlank
    @Size(min=6, max=40)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     */

}
