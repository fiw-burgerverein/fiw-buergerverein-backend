package com.buergervereinHSH.BackendProject.auth.service;

import com.buergervereinHSH.BackendProject.auth.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDao;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDaoImpl;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.LoginDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.SignUpDto;
import com.buergervereinHSH.BackendProject.auth.exceptions.*;
import com.buergervereinHSH.BackendProject.auth.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDaoImpl userDaoImpl;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public ApiResponse signUp(SignUpDto signUpDto) {
        validateSignUp(signUpDto);
        User user = new User();

        if(!signUpDto.getEmail().equals(signUpDto.getEmailConfirm())) {
            throw new EmailMismatchException();
        }
        if(!signUpDto.getPassword().equals(signUpDto.getPasswordConfirm())) {
            throw new PasswordMismatchException();
        }
        if (userDao.findByEmail(signUpDto.getEmail()) != null) {
            throw new EmailAlreadyInUseException();
        }

        BeanUtils.copyProperties(signUpDto, user);
        user.setPassword(encoder.encode(user.getPassword()));
        userDaoImpl.save(user);
        return new ApiResponse(200, "Sie sind erfolgreich registriert.", user);
    }

    @Override
    public ApiResponse login(LoginDto loginDto) {
        User user = userDao.findByEmail(loginDto.getEmail());

        if(user == null) {
            throw new NoUserFoundException();
        }
        if(!encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }

        return new ApiResponse(200, "Sie sind jetzt eingeloggt!", null) ;

    }

    private void validateSignUp(SignUpDto signUpDto) {
    }
}
