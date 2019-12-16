package com.buergervereinHSH.BackendProject.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String email;
    @JsonIgnore
    private String password;
    @ManyToMany
    private Set<Role> roles;
    private boolean enabled;

    @JsonIgnore
    private String resetToken;
    @JsonIgnore
    private LocalDateTime resetTokenExpiryDate;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private VerificationToken verificationToken;

    public User(){
    }

    public User(String email, String password, Set<Role> roles, boolean enabled) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;
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

    public String getResetToken() { return resetToken; }
    public void setResetToken(String resetToken) { this.resetToken = resetToken; }

    public LocalDateTime getResetTokenExpiryDate() { return resetTokenExpiryDate; }
    public void setResetTokenExpiryDate(LocalDateTime resetTokenExpiryDate) { this.resetTokenExpiryDate = resetTokenExpiryDate; }

}
