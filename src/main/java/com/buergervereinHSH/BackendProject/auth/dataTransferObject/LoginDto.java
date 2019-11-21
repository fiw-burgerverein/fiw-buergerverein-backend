package com.buergervereinHSH.BackendProject.auth.dataTransferObject;

import com.buergervereinHSH.BackendProject.auth.model.Role;

public class LoginDto {

    private String email;
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
