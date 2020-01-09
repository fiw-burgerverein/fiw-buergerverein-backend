package com.buergervereinHSH.BackendProject.auth.dataAccessObject;

import com.buergervereinHSH.BackendProject.auth.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Long> {

    //User findByEmail(String email);
    User save(User user);
    User findByResetToken(String resetToken);

    User findByUserId(long userId);

    void deleteById(long user_id);

    User findByEmail(String email);
    Boolean existsByEmail(String email);

}
