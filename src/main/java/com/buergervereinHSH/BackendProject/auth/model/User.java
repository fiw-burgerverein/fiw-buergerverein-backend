package com.buergervereinHSH.BackendProject.auth.model;

import com.buergervereinHSH.BackendProject.auth.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(min=6, max=100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
    joinColumns  = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(){}


    public User(String email, String password){
        this.email = email;
        this.password = password;
    }


    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }


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

    public Set<Role> getRoles() {

        return roles;
    }
    public void setRoles(Set<Role> roles) {

        this.roles = roles;
    }

}
