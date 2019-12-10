package com.buergervereinHSH.BackendProject.auth.dataAccessObject;

import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.auth.model.VerificationToken;
/*import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;*/
import org.springframework.data.repository.CrudRepository;

/*import java.util.Date;
import java.util.stream.Stream;*/

public interface VerificationTokenDao extends CrudRepository<VerificationToken, Long>  {

    VerificationToken findByToken(String token);
    VerificationToken save(VerificationToken token);
    VerificationToken findByUser(User user);


    //not necessary now:
/*    Stream<VerificationToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from VerificationToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);*/
}

//StackAbuse:
/*
import org.springframework.data.repository.CrudRepository;
import com.buergervereinHSH.BackendProject.auth.model.VerificationToken;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, String> {

    VerificationToken findByVerificationToken(String verificationToken);
}
*/
