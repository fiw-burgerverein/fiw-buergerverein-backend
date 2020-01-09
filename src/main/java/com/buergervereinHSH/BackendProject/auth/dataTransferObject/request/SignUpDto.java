package com.buergervereinHSH.BackendProject.auth.dataTransferObject.request;

import com.buergervereinHSH.BackendProject.auth.model.Role;

import javax.validation.constraints.*;
import java.util.Set;

public class SignUpDto {

    @NotBlank @NotNull @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    @Email(message = "Bitte geben Sie eine gültige Email-Adresse ein.")
    private String email;
  /*  @NotBlank @NotNull @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String emailConfirm;*/
    @NotBlank @NotNull @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    @Size(min=8, max=32, message="Bitte wählen Sie einen Passwort, der länger als 8 Symbole und kürzer als 32 Symbole ist.")
    private String password;
    /*@NotBlank @NotNull @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String passwordConfirm;*/
    private Set<String> role;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

   /* public String getEmailConfirm() { return emailConfirm; }
    public void setEmailConfirm(String emailConfirm) { this.emailConfirm = emailConfirm; }

    public String getPasswordConfirm() { return passwordConfirm; }
    public void setPasswordConfirm(String passwordConfirm) { this.passwordConfirm = passwordConfirm; }
    */
   public Set<String> getRole() {
       return role;
   }

    public void setRole(Set<String> role) {
        this.role = role;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) { this.password = password; }

    /*public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }*/


    /*
     @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     */
}
