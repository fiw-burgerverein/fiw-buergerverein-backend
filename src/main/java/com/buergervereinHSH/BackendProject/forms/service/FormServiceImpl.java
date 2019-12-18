package com.buergervereinHSH.BackendProject.forms.service;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.forms.dataAccessObject.AufwandDao;
import com.buergervereinHSH.BackendProject.forms.dataAccessObject.FormDao;
import com.buergervereinHSH.BackendProject.forms.dataAccessObject.SachkostenDao;
import com.buergervereinHSH.BackendProject.forms.dataTransferObject.request.FormDto;
import com.buergervereinHSH.BackendProject.forms.model.Aufwand;
import com.buergervereinHSH.BackendProject.forms.model.Formular;
import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Override
    public ApiResponse saveForm(FormDto formDto) {
        Formular formular = new Formular();
//        formular.setUserId(userId);
        BeanUtils.copyProperties(formDto, formular, "sachkosten", "sachkostenSum", "aufwand", "aufwandSum");
        formular.setCreatedAt(LocalDateTime.now());
//        formDao.save(formular);

        float sachkostenGesamt = 0;
        Sachkosten[] sachkostenArray = formDto.getSachkostenArray();
        for (Sachkosten value : sachkostenArray) {      // ??
            Sachkosten sachkosten = new Sachkosten();
            BeanUtils.copyProperties(value, sachkosten);
//            sachkosten.setFormId(formular.getFormId());
            sachkostenDao.save(sachkosten);

            sachkostenGesamt += value.getCost();
        }
        formular.setSachkostenSum(sachkostenGesamt);
        formDao.save(formular);
//        for (int i = formDto.getSachkosten().size() / 2; i > 0; i--) { //weiss nicht genau wie das zu loesen
//            Sachkosten sachkosten = new Sachkosten();
//            BeanUtils.copyProperties(formDto.getSachkosten[i](), sachkosten, "projectName", "beschreibung", "startDate", "endDate",
//                    "ort", "zielgruppe", "anzTeilnehmer", "activities", "activitiesBeschreibung", "aufwand", "anrede", "vorname",
//                    "nachname", "einrichtung", "strasse", "hausNr", "plz", "email", "telNr");
//            sachkosten.setFormId(formular.getFormId());
//            sachkostenDao.save(sachkosten);
//        }
//        float aufwandGesamt = 0;
//        List<Aufwand> aufwandTable = aufwandDao.findByFormId(formular.getFormId());
//        for (int i = 1; i < aufwandTable.size(); i ++) {
//            aufwandGesamt =+ aufwandTable.get(i).getCost();
//        }
//        formular.setAufwandSum(aufwandGesamt);


//        eigentlich noch nicht da der Antrag noch in PDF umgewandelt werden soll und abgeschickt :/
        return new ApiResponse(200, "Sie haben erfolgreich Ihren Antrag abgesendet!", null);
    }

}
