package com.buergervereinHSH.BackendProject.forms.service;

import com.buergervereinHSH.BackendProject.auth.dataAccessObject.UserDao;
import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.forms.dataAccessObject.AufwandDao;
import com.buergervereinHSH.BackendProject.forms.dataAccessObject.FormDao;
import com.buergervereinHSH.BackendProject.forms.dataAccessObject.SachkostenDao;
import com.buergervereinHSH.BackendProject.forms.dataTransferObject.request.FormDto;
import com.buergervereinHSH.BackendProject.forms.model.Aufwand;
import com.buergervereinHSH.BackendProject.forms.model.Formular;
import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;
import com.buergervereinHSH.BackendProject.forms.model.Status;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

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

    @Override
    public ApiResponse saveForm(long userId, FormDto formDto) {
        Formular formular = new Formular();
        User user = userDao.findByUserId(userId);
        formular.setUser(user);
        BeanUtils.copyProperties(formDto, formular, "sachkosten", "sachkostenSum", "aufwand", "aufwandSum");
        formular.setCreatedAt(LocalDateTime.now());
        formular.setStatus(Status.IN_BEARBEITUNG);
        formDao.save(formular);
//
//        float sachkostenGesamt = 0;
//        Sachkosten[] sachkostenArray = formDto.getSachkostenArray();
//        for (Sachkosten value : sachkostenArray) {
//            Sachkosten sachkosten = new Sachkosten();
//            BeanUtils.copyProperties(value, sachkosten);
//            sachkosten.setForm(formular);
//            sachkostenDao.save(sachkosten);
//
//            sachkostenGesamt += value.getCost();
//        }
//
//        float aufwandGesamt = 0;
//        Aufwand[] aufwandArray = formDto.getAufwandArray();
//        for (Aufwand value : aufwandArray) {
//            Aufwand aufwand = new Aufwand();
//            BeanUtils.copyProperties(value, aufwand);
//            aufwand.setForm(formular);
//            aufwandDao.save(aufwand);
//
//            aufwandGesamt += value.getCost();
//        }
//
//        formular.setAufwandSum(aufwandGesamt);
//        formular.setSachkostenSum(sachkostenGesamt);
//        formDao.save(formular);

//        eigentlich noch nicht da der Antrag noch in PDF umgewandelt werden soll und abgeschickt :/
        return new ApiResponse(200, "Sie haben erfolgreich Ihren Antrag abgesendet!", null);
    }

}
