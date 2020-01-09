//package com.buergervereinHSH.BackendProject.auth.security.services;
//
//import com.buergervereinHSH.BackendProject.auth.model.User;
//import com.buergervereinHSH.BackendProject.auth.repository.UserRepository;
//import org.apache.naming.factory.SendMailFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.nio.file.attribute.UserPrincipal;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email).orElseThrow(
//                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + email));
//
//        return UserPrinciple.build(user);
//    }
//}
