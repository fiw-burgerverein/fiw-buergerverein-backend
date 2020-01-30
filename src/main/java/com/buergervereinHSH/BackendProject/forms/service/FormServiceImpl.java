package com.buergervereinHSH.BackendProject.forms.service;

import com.buergervereinHSH.BackendProject.auth.dataAccessObject.GeschStellenDao;
import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDao;
import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.GeschStellenDto;
import com.buergervereinHSH.BackendProject.auth.model.User;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
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

    @Override
    public ApiResponse saveForm(long userId, FormDto formDto) {

        Formular formular = new Formular();
        User user = userDao.findByUserId(userId);
        formular.setUser(user);
        BeanUtils.copyProperties(formDto, formular, "sachkosten", "sachkostenSum", "aufwand", "aufwandSum");
        formular.setCreatedAt(LocalDateTime.now());
        formular.setStatus(Status.IN_BEARBEITUNG);
        formDao.save(formular);

        float sachkostenGesamt = 0;
        Sachkosten[] sachkostenArray = formDto.getSachkostenArray();
        for (Sachkosten value : sachkostenArray) {
            Sachkosten sachkosten = new Sachkosten();
            BeanUtils.copyProperties(value, sachkosten);
            sachkosten.setForm(formular);
            sachkostenDao.save(sachkosten);

            sachkostenGesamt += value.getCost();
        }

        float aufwandGesamt = 0;
        Aufwand[] aufwandArray = formDto.getAufwandArray();
        for (Aufwand value : aufwandArray) {
            Aufwand aufwand = new Aufwand();
            BeanUtils.copyProperties(value, aufwand);
            aufwand.setForm(formular);
            aufwandDao.save(aufwand);

            aufwandGesamt += value.getCost();
        }

        formular.setAufwandSum(aufwandGesamt);
        formular.setSachkostenSum(sachkostenGesamt);
        formDao.save(formular);

/*        // Email an entsprechende GS:
        String GSName = formular.getOrt();
        GeschStelle geschStelle = geschStellenDao.findByName(GSName);
        String emailGS = geschStelle.getEmail();

        emailImpl.sendSimpleMessage(emailGS, "Neuer Antrag eingegangen",
                "Ein neu eingegangener Antrag liegt für Sie zum Download bereit.");*/

        return new ApiResponse(200, "Sie haben erfolgreich Ihren Antrag abgesendet!", null);
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
    public ApiResponse changeState(long formId, GeschStellenDto geschStelleDto) {

        Formular formular = formDao.findByFormId(formId);

        int state = geschStelleDto.getState();
        if(state==1) {formular.setStatus(Status.GENEHMIGT);}
        else if(state==2) {formular.setStatus(Status.ABGELEHNT);}
        else {formular.setStatus(Status.IN_BEARBEITUNG);}

        return new ApiResponse(200, "Der Status des Antrags wurde erfolgreich geändert", formular);
    }


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
    public ApiResponse getAllForms() {  //evtl noch anpassen: nur AllFormsDto wird übergeben, statt aller Daten der Formulare

        List<Formular> allForms = formDao.getAllForms();

        return new ApiResponse(200, "Alle Antraege erfolgreich übermittelt", allForms);

    }

}
