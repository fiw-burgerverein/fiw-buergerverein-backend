package com.buergervereinHSH.BackendProject.auth.dataAccessObject;

import com.buergervereinHSH.BackendProject.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);
}

