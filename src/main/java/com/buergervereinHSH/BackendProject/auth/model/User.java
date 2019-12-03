package com.buergervereinHSH.BackendProject.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //maybe @Generated(strategy = GenerationType.AUTO)? (sets primary key to auto-increment)
    @Column(name="user_id")  //new
    private long user_id;
    private String email;
    @JsonIgnore
    private String password;
    @ManyToMany
    private Set<Role> roles;
    @Column(name = "enabled")  //new
    private boolean enabled; //= false;?

    public User() {  //new
        super();
        this.enabled=false;
    }

    public long getUser_id() {return user_id;}
    public void setUser_id(long user_id) {this.user_id = user_id;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password; }
    public void setPassword(String password) {this.password = password;}

    public Set<Role> getRoles() { return roles;}
    public void setRoles(Set<Role> roles) { this.roles = roles;}

    public boolean isEnabled() {return enabled;}
    public void setEnabled(boolean enabled) { this.enabled = enabled;}


}
