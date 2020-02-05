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
import java.util.HashMap;
import java.util.Map;

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

        Map<String, Object> result = new HashMap<String,Object>();
        result.put("formId",formular.getFormId());
        result.put("createdAt", formular.getCreatedAt());
        return new ApiResponse(200, "Sie haben erfolgreich Ihren Antrag abgesendet!", result);
    }

}
