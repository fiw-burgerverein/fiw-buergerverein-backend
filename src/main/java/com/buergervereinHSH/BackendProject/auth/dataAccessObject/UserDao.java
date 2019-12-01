package com.buergervereinHSH.BackendProject.auth.dataAccessObject;

//Ksenias Code, works with sessions and entity manager
/*import com.buergervereinHSH.BackendProject.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);
}*/

    //Spring Data provides basic CRUD operations (

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.buergervereinHSH.BackendProject.auth.model.User;


@Repository("userRepository")  //
public interface UserDao extends CrudRepository<User, String> {

        User findByEmailIdIgnoreCase(String email);
    }