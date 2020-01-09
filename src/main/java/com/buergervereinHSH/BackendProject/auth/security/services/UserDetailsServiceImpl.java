package com.buergervereinHSH.BackendProject.auth.security.services;

import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDao;
import com.buergervereinHSH.BackendProject.auth.exceptions.NoUserFoundException;
import com.buergervereinHSH.BackendProject.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;

  /*  @Override
    @Transactional
    public UserPrinciple loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userDao.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + email)));

        return UserPrinciple.build(user);
    }*/

  @Override
  @Transactional
    public UserPrinciple loadUserByUsername(String email){
      User user = userDao.findByEmail(email);
      if(user == null){
          throw new NoUserFoundException();

      }
      return UserPrinciple.build(user);
  }
}
