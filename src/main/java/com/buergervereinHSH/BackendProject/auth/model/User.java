package com.buergervereinHSH.BackendProject.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;

import java.time.LocalDateTime;

import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @NaturalId
    @NotBlank
    @Size(max = 50)
    private long userId;
    private String email;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    private boolean enabled;

  /*  @ManyToMany
    private Set<Role> roles;
*/


    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /*public User(String email, String password, Set<Role> roles) {
    }*/


  /*  public User(String email, String password){
        this.email = email;
        this.password = password;
    }*/



    @JsonIgnore
    private String resetToken;
    @JsonIgnore
    private LocalDateTime resetTokenExpiryDate;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private VerificationToken verificationToken;



    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public boolean isEnabled() {return enabled;}
    public void setEnabled(boolean enabled) { this.enabled = enabled;}

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public LocalDateTime getResetTokenExpiryDate() {
        return resetTokenExpiryDate;
    }

    public void setResetTokenExpiryDate(LocalDateTime resetTokenExpiryDate) {
        this.resetTokenExpiryDate = resetTokenExpiryDate;
    }

}
