package com.buergervereinHSH.BackendProject.auth.dataAccessObject;

import com.buergervereinHSH.BackendProject.auth.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    User findByEmail(String email);
    User save(User user);
    User findByResetToken(String resetToken);
    void deleteById(long user_id);


}
