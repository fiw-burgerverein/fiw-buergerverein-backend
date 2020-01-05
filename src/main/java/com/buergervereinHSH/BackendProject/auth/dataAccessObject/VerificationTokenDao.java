package com.buergervereinHSH.BackendProject.auth.dataAccessObject;

import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.auth.model.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenDao extends CrudRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);
    VerificationToken save(VerificationToken token);
    VerificationToken findByUser(User user);

}