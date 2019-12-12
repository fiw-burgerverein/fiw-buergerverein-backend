package com.buergervereinHSH.BackendProject.auth.dataAccessObject;

import com.buergervereinHSH.BackendProject.auth.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    //User findByEmailIdIgnoreCase(String email);
    User findByEmail(String email);
    User save(User user);
    User findByResetToken(String resetToken);
    User deleteByEmail(String mail);

    //User findByUser_id(Long user_id);



}
