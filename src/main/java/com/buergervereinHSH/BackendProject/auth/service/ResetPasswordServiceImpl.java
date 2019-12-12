package com.buergervereinHSH.BackendProject.auth.service;

import com.buergervereinHSH.BackendProject.auth.exceptions.NoUserFoundException;
import com.buergervereinHSH.BackendProject.auth.exceptions.PasswordMismatchException;
import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDao;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.ForgotPasswordDto;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.ResetPasswordDto;
import com.buergervereinHSH.BackendProject.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private EmailServiceImpl emailImpl;
    @Autowired
    HttpServletRequest request;

    @Override
    public ApiResponse sendResetToken(ForgotPasswordDto forgotPasswordDto) {

        User user = userDao.findByEmail(forgotPasswordDto.getEmail());
        if(user == null) {  //runtime exceptions werde ich spaeter in exception-Klassen umwandeln
            throw new NoUserFoundException();
        }

// erstmal auskommentiert da es unmöglich war, sonst den user zu checken
//        if(user.isEnabled()==false)
//        {
//            throw new RuntimeException("Bitte bestätigen Sie Ihre Email-Adresse anhand des Bestätigungslinks in Ihrem Email Postfach");
//        }

        String resetToken = UUID.randomUUID().toString();   //token erzeugen
        LocalDateTime expiryDate = LocalDateTime.now().plusSeconds(60 * 60 * 24 * 3);   // valid for 3 days
        user.setResetToken(resetToken);
        user.setResetTokenExpiryDate(expiryDate);
        userDao.save(user);     //kann auch eine update-Methode sein, aber save() scheint zu reichen
        String reset_url = "http://" + request.getServerName() + request.getServerPort() + request.getContextPath()
                + "/reset-password?token=" + resetToken;
        emailImpl.sendSimpleMessage(user.getEmail(), "Reset Password", "Durch Betätigen des Links: " +reset_url);
        return new ApiResponse(200, "Ein Link wurde an die von Ihnen angebenen Email gesendet.", user);
    }

//    @Override
//    public ApiResponse checkResetToken (ResetPasswordDto resetPasswordDto) {
//
//        User user = userDao.findByResetToken(resetPasswordDto.getResetToken());
//        if(user == null) {
//            throw new NoUserFoundException();
//        }
//        if (user.getResetTokenExpiryDate().isBefore(LocalDateTime.now())) {
//            throw new RuntimeException("Dieses Link ist nicht mehr aktuell.");
//        }
//        return new ApiResponse(200, "Link valid, proceed.", user);
//    }

    @Override
    public ApiResponse checkResetToken (String resetToken) {

        User user = userDao.findByResetToken(resetToken);
        if(user == null) {
            throw new NoUserFoundException();
        }
        if (user.getResetTokenExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Dieses Link ist nicht mehr aktuell.");
        }
        return new ApiResponse(200, "Link valid", user);
    }


    @Override
    public ApiResponse saveNewPassword(ResetPasswordDto resetPasswordDto) {

        User user = userDao.findByResetToken(resetPasswordDto.getResetToken());
        if(!resetPasswordDto.getPassword().equals(resetPasswordDto.getPasswordConfirm())) {
            throw new PasswordMismatchException();
        }
        user.setPassword(encoder.encode(resetPasswordDto.getPassword()));
        user.setResetTokenExpiryDate(LocalDateTime.now());
        userDao.save(user);
        return new ApiResponse(200, "Sie haben erfolgreich Ihren Passwort verändert!", user);
    }

}
