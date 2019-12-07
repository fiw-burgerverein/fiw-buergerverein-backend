package com.buergervereinHSH.BackendProject.auth.service;

import com.buergervereinHSH.BackendProject.auth.ApiResponse;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDao;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDaoImpl;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.VerificationTokenRepository;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.ForgotPasswordDto;
import com.buergervereinHSH.BackendProject.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDaoImpl userDaoImpl;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private EmailServiceImpl emailImpl;

    @Override
    public ApiResponse sendResetToken(ForgotPasswordDto forgotPasswordDto) {

        User user = userDao.findByEmail(forgotPasswordDto.getEmail());
        if(user == null) {  //runtime exceptions werde ich spaeter in exception-Klassen umwandeln
            throw new RuntimeException("Dieser Account existiert nicht");
        }
//        if(user.isEnabled()==false)
//        {
//            throw new RuntimeException("Bitte bestätigen Sie Ihre Email-Adresse anhand des Bestätigungslinks in Ihrem Email Postfach");
//        }
        String resetToken = UUID.randomUUID().toString();   //token erzeugen
        LocalDateTime expiryDate = LocalDateTime.now().plusSeconds(60 * 60 * 24 * 3);   // valid for 3 days
        user.setResetToken(resetToken);
        user.setResetTokenExpiryDate(expiryDate);
        userDaoImpl.save(user);     //kann noch eine update-Methode sein, aber save() scheint zu reichen
        emailImpl.sendSimpleMessage(user.getEmail(), "Reset Password", "Durch Betätigen" +
                "des Links: "+resetToken);
        return new ApiResponse(200, "Ein Link wurde an die von Ihnen angebenen Email gesendet.", user);
    }
}
