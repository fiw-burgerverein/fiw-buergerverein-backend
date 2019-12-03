package com.buergervereinHSH.BackendProject.auth.service;

import com.buergervereinHSH.BackendProject.auth.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDao;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDaoImpl;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.VerificationTokenRepository;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.LoginDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.SignUpDto;
import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.auth.model.VerificationToken;
import com.buergervereinHSH.BackendProject.auth.service.EmailService;
import com.buergervereinHSH.BackendProject.auth.service.EmailServiceImpl;
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
    @Autowired
    private UserDaoImpl userDaoImpl;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired //neu
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private EmailServiceImpl emailImpl;

    @Override
    public ApiResponse signUp(SignUpDto signUpDto) {  //fehlt methode zur bestätigung des Links
        validateSignUp(signUpDto);
        User user = new User();   //Objekterzeugung in Spring

        if(!signUpDto.getEmail().equals(signUpDto.getEmailConfirm())) {
            throw new RuntimeException("Die eingegebene Email-Adressen stimmen nicht überein.");
        }
        if(!signUpDto.getPassword().equals(signUpDto.getPasswordConfirm())) {
            throw new RuntimeException("Die eingegebene Passwörter stimmen nicht überein.");
        }
        if (!signUpDto.getEmail().matches("^([a-zA-Z0-9_\\-\\.]+)@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$")) {
            throw new RuntimeException("Bitte geben Sie eine gültige Email-Adresse ein.");
        }
        if (signUpDto.getPassword().length() < 8 || signUpDto.getPassword().length() > 32) {
            throw new RuntimeException("Bitte wählen Sie einen Passwort, der länger als 8 Symbole und kürzer als 32 Symbole ist.");
        }
        if (userDao.findByEmail(signUpDto.getEmail()) != null) {
            throw new RuntimeException("Diese Email-Adresse wird von einem anderen Account benutzt.");
        }

        BeanUtils.copyProperties(signUpDto, user);
        user.setPassword(encoder.encode(user.getPassword()));
        userDaoImpl.save(user);

        String token = UUID.randomUUID().toString();  //erstellen eines random Strings (als Token)
        createVerificationTokenForUser(user, token);  //erstellen eines VerificationTokens mit token als String

        //zum testen, noch ohne URL in Email;
        emailImpl.sendSimpleMessage(user.getEmail(), "Confirmation Registration", "Durch Betätigen" +
                "des Links: "+token);

        return new ApiResponse(200, "Ein Besätigungslink wurde an die von Ihnen angebenen Email gesendet.", user); //eigentlich noch nicht
    }

    @Override
    public ApiResponse login(LoginDto loginDto) {

        User user = userDao.findByEmail(loginDto.getEmail());

        //if user.enabled==true:

        if(loginDto.getEmail() == null || loginDto.getPassword() == null) {
            throw new RuntimeException("Bitte füllen Sie die Email- und Passwort-Felder aus.");
        }
        if(user == null) {
            throw new RuntimeException("Dieser Account existiert nicht.");
        }
        if(!encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Falsches Passwort.");
        }
        return new ApiResponse(200, "Sie sind jetzt eingeloggt!", null) ;

    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        VerificationToken myToken = new VerificationToken(user, token);
        tokenRepository.save(myToken);
        //return new ApiResponse(200, "Token was created", null);
    }

    /*@Override
    public void setEmailParam(User user, VerificationToken token) {

    }*/

   /* @Override   //alten überschreiben, fehlt updateMethode (unr reicht?)
    public VerificationToken generateNewVerificationToken(String existingVerificationToken) {
        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID()
                .toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }*/

    private void validateSignUp(SignUpDto signUpDto) {
    }
}
