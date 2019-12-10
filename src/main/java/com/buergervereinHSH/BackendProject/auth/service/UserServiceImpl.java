package com.buergervereinHSH.BackendProject.auth.service;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDao;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.VerificationTokenDao;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.LoginDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.SignUpDto;
import com.buergervereinHSH.BackendProject.auth.exceptions.*;
import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.auth.model.VerificationToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;
   /* @Autowired
    private UserDaoImpl userDaoImpl;*/
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private VerificationTokenDao verificationTokenDao;
    @Autowired
    private EmailServiceImpl emailImpl;

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
        user.setEnabled(false);
        userDao.save(user);

        String token = UUID.randomUUID().toString();  //erstellen eines random Strings (als Token)
        createVerificationTokenForUser(user, token);  //erstellen eines VerificationTokens mit token als String

        //zum testen, noch ohne URL in Email;
        emailImpl.sendSimpleMessage(user.getEmail(), "Confirmation Registration", "Bitte bestätigen Sie Ihren Account " +
                "durch Betätigen des Links: http://localhost:8080/accountbestaetigung "+token);
        return new ApiResponse(200, "Ein Bestätigungslink wurde an die von Ihnen angebenen Email gesendet.", user);
    }

    @Override
    public ApiResponse login(LoginDto loginDto) {

        User user = userDao.findByEmail(loginDto.getEmail());

        if(user == null) {  //noch nicht freigeschaltet --> eigene exception schmeißen besser
            throw new NoUserFoundException();
        }
        if(user.isEnabled()==false)
        {
            throw new AccountNotActivatedException();
        }
        if(!encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }

        return new ApiResponse(200, "Sie sind jetzt eingeloggt!", null) ;

    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        VerificationToken myToken = new VerificationToken(user, token);
        verificationTokenDao.save(myToken);
        //return new ApiResponse(200, "Token was created", null);
    }

    private void validateSignUp(SignUpDto signUpDto) {
    }

    /*@Override
    public void setEmailParam(User user, VerificationToken token) {

    }*/

   /* @Override   //alten Token überschreiben
    public VerificationToken generateNewVerificationToken(String existingVerificationToken) {
        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID()
                .toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }*/

}
