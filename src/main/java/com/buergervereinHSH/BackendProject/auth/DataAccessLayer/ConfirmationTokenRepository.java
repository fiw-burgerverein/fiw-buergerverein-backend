package com.buergervereinHSH.BackendProject.auth.DataAccessLayer;

import org.springframework.data.repository.CrudRepository;

import com.buergervereinHSH.BackendProject.auth.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
