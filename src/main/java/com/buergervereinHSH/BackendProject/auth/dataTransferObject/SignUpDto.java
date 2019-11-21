package com.buergervereinHSH.BackendProject.auth.dataTransferObject;

import com.buergervereinHSH.BackendProject.auth.model.Role;

public class SignUpDto {

    private String email;
    private String emailConfirm;
    private String password;
    private String passwordConfirm;
    private Role role;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailConfirm() { return emailConfirm; }
    public void setEmailConfirm(String emailConfirm) { this.emailConfirm = emailConfirm; }

    public String getPasswordConfirm() { return passwordConfirm; }
    public void setPasswordConfirm(String passwordConfirm) { this.passwordConfirm = passwordConfirm; }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
