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

        BeanUtils.copyProperties(formDto, formular, "sachkosten", "aufwand");

        for (int i = formDto.getSachkosten().length / 2; i > 0; i--) { //weiss nicht genau wie das zu loesen
            Sachkosten sachkosten = new Sachkosten();
            BeanUtils.copyProperties(formDto, sachkosten, "projectName", "beschreibung", "startDate", "endDate",
                    "ort", "zielgruppe", "anzTeilnehmer", "activities", "activitiesBeschreibung", "aufwand", "anrede", "vorname",
                    "nachname", "einrichtung", "strasse", "hausNr", "plz", "email", "telNr");
            sachkosten.setFormId(formular.getFormId());
            sachkostenDao.save(sachkosten);
        }
        for (int i = formDto.getAufwand().length / 2; i > 0; i--) { //weiss nicht genau wie das zu loesen
            Aufwand aufwand = new Aufwand();
            BeanUtils.copyProperties(formDto, aufwand, "projectName", "beschreibung", "startDate", "endDate",
                    "ort", "zielgruppe", "anzTeilnehmer", "activities", "activitiesBeschreibung", "sachkosten", "anrede", "vorname",
                    "nachname", "einrichtung", "strasse", "hausNr", "plz", "email", "telNr");
            aufwand.setFormId(formular.getFormId());
            aufwandDao.save(aufwand);
        }

//  for (sachkostenDao.findByFormId(formular.getFormId)) {
//        i =+ sachkostenDao.findByFormId(formular.getFormId).getKosten()
//        return i;
//    }
//   formular.setSachkostenSum(i);

        formDao.save(formular);

//        eigentlich noch nicht da der Antrag noch in PDF umgewandelt werden soll und abgeschickt :/
        return new ApiResponse(200, "Sie haben erfolgreich Ihren Antrag abgesendet!", null);
    }

//    public static void copyProperties2(Object src, Object trg, Set<String> props) {
//        String[] excludedProperties =
//                Arrays.stream(BeanUtils.getPropertyDescriptors(src.getClass()))
//                        .map(PropertyDescriptor::getName)
//                        .filter(name -> !props.contains(name))
//                        .toArray(String[]::new);
//
//        BeanUtils.copyProperties(src, trg, excludedProperties);
//    }

}
