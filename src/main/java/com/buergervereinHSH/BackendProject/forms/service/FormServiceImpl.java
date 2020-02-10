package com.buergervereinHSH.BackendProject.forms.service;

import com.buergervereinHSH.BackendProject.auth.dataAccessObject.GeschStellenDao;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDao;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.ChangeStateDto;
import com.buergervereinHSH.BackendProject.auth.model.GeschStelle;
import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.auth.security.jwt.JwtAuthTokenFilter;
import com.buergervereinHSH.BackendProject.auth.security.jwt.JwtProvider;
import com.buergervereinHSH.BackendProject.auth.service.EmailServiceImpl;
import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.forms.dataAccessObject.AufwandDao;
import com.buergervereinHSH.BackendProject.forms.dataAccessObject.FormDao;
import com.buergervereinHSH.BackendProject.forms.dataAccessObject.SachkostenDao;
import com.buergervereinHSH.BackendProject.forms.dataTransferObject.request.FormDto;
import com.buergervereinHSH.BackendProject.forms.dataTransferObject.request.SingleFormDto;
import com.buergervereinHSH.BackendProject.forms.model.Aufwand;
import com.buergervereinHSH.BackendProject.forms.model.Formular;
import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;
import com.buergervereinHSH.BackendProject.forms.model.Status;
import com.buergervereinHSH.BackendProject.message.response.ResponseMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Transactional
@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormDao formDao;
    @Autowired
    private SachkostenDao sachkostenDao;
    @Autowired
    private AufwandDao aufwandDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private GeschStellenDao geschStellenDao;
    @Autowired
    private FormMailServiceImpl formMailServiceImpl;
    @Autowired
    private EmailServiceImpl emailImpl;
    @Autowired
    SingleFormDto singleFormDto;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtAuthTokenFilter jwtAuthTokenFilter;

    @Override
    public ApiResponse saveForm(HttpServletRequest request, FormDto formDto) {

        Long userId = getUserIdfromToken(request);

        Formular formular = new Formular();
        User user = userDao.findByUserId(userId);
        formular.setUser(user);
        BeanUtils.copyProperties(formDto, formular, "sachkosten", "aufwand", "gesamtkosten");
        formular.setCreatedAt(LocalDateTime.now());
        formular.setStatus(Status.IN_BEARBEITUNG);
        formDao.save(formular);
        float gesamtkosten = 0;

//        float sachkostenGesamt = 0;
        Sachkosten[] sachkostenArray = formDto.getSachkostenArray();
        for (Sachkosten value : sachkostenArray) {
            Sachkosten sachkosten = new Sachkosten();
            BeanUtils.copyProperties(value, sachkosten);
            sachkosten.setForm(formular);
            sachkostenDao.save(sachkosten);

            gesamtkosten += value.getCost();
//            sachkostenGesamt += value.getCost();
        }

//        float aufwandGesamt = 0;
        Aufwand[] aufwandArray = formDto.getAufwandArray();
        for (Aufwand value : aufwandArray) {
            Aufwand aufwand = new Aufwand();
            BeanUtils.copyProperties(value, aufwand);
            aufwand.setForm(formular);
            aufwandDao.save(aufwand);

            gesamtkosten += value.getCost();
//            aufwandGesamt += value.getCost();
        }

//        formular.setAufwandSum(aufwandGesamt);
//        formular.setSachkostenSum(sachkostenGesamt);
        formular.setGesamtkosten(gesamtkosten);
        formDao.save(formular);

              // Email an entsprechende GS:
        String GSName = formular.getOrt();
        GeschStelle geschStelle = geschStellenDao.findByName(GSName);
        String emailGS = geschStelle.getEmail();

        emailImpl.sendSimpleMessage(emailGS, "Neuer Antrag eingegangen",
                "Ein neu eingegangener Antrag liegt für Sie zum Download bereit.");
      
        Map<String, Object> result = new HashMap<String,Object>();
        result.put("formId",formular.getFormId());
        result.put("createdAt", formular.getCreatedAt());
        return new ApiResponse(200, "Sie haben erfolgreich Ihren Antrag abgesendet!", result);
    }

    @Override
    public ApiResponse sendPDFtoUser(long userId) throws MessagingException {

        User user = userDao.findByUserId(userId);

        //Parameter ändern!
        try {
            formMailServiceImpl.sendMailWithAttachment(user.getEmail(), "Mail mit Anhang", "Im Anhang: Antrags als PDF",
                "src/main/resources/test.pdf");
            return new ApiResponse(200, "Email wurde versendet", null);
        } catch (MailException mailException) {
            System.out.println(mailException);
            return new ApiResponse(404, "Email konnte nicht gesendet werden", null);
        }

    }

    @Override
    public ResponseEntity<?> changeState(ChangeStateDto changeStateDto) {

        long formId = changeStateDto.getFormId();
        Formular formular = formDao.findByFormId(formId);

        int state = changeStateDto.getIntState();
        if(state==1) {formular.setStatus(Status.GENEHMIGT);}
        else if(state==2) {formular.setStatus(Status.ABGELEHNT);}
        else {formular.setStatus(Status.IN_BEARBEITUNG);}

        return new ResponseEntity(new ResponseMessage("Status des Antrags erfolgreich geändert"), HttpStatus.OK);
    }
    /*@Override
    public ResponseEntity<?> changeState(String formId, int statusInt) {

        long LongFormid = Long.parseLong( formId );
        Formular formular = formDao.findByFormId(LongFormid);

        int state = statusInt;
        if(state==1) {formular.setStatus(Status.GENEHMIGT);}
        else if(state==2) {formular.setStatus(Status.ABGELEHNT);}
        else {formular.setStatus(Status.IN_BEARBEITUNG);}

        return new ResponseEntity(new ResponseMessage("Status des Antrags erfolgreich geändert"), HttpStatus.OK);
    }*/


    @Override
    public ApiResponse getSingleForm(long formId) {


        Formular form = formDao.findByFormId(formId);
        singleFormDto.setFormular(form);

        Sachkosten[] currSachkostenArray = sachkostenDao.findByForm(form);
        singleFormDto.setSachkostenArray(currSachkostenArray);

        Aufwand[] currAufwandArray = aufwandDao.findByForm(form);
        singleFormDto.setAufwandArray(currAufwandArray);

        return new ApiResponse(200, "Antrag erfolgreich übermittelt", singleFormDto);
    }

    @Override
    public Long getUserIdfromToken(HttpServletRequest request) {

        String jwt = jwtAuthTokenFilter.getJwt(request);
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userDao.findByEmail(email);
        Long user_id = user.getUserId();
        return user_id;
    }

    @Override
    public ApiResponse getAllForms() {  //evtl noch anpassen: nur AllFormsDto wird übergeben, statt aller Daten der Formulare

        List<Formular> allForms = formDao.getAllForms();

        return new ApiResponse(200, "Alle Antraege erfolgreich übermittelt", allForms);

    }

    @Override
    public ResponseEntity<ApiResponse> getAllFormsOfUser(HttpServletRequest request) {
        Long userId = getUserIdfromToken(request);
        User user = userDao.findByUserId(userId);

        List<Formular> allForms = formDao.getFormularByUser(user);

        return ResponseEntity.ok(new ApiResponse(200, "Alle Anträge übermittelt", allForms));
    }

}
