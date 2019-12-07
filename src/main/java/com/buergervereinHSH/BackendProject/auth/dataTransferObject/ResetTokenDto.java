package com.buergervereinHSH.BackendProject.auth.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class ResetTokenDto {
    @JsonIgnore //brauchen wir das?
    private String resetToken;
    @JsonIgnore
    private LocalDateTime resetTokenExpiryDate;

    public LocalDateTime getResetTokenExpiryDate() {
        return resetTokenExpiryDate;
    }
    public String getResetToken() {
        return resetToken;
    }
}
