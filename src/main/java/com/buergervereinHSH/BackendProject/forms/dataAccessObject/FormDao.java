package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.forms.model.Formular;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormDao extends CrudRepository<Formular, Long> {

    Formular findByFormId(long formId);
    Formular findByEmail(String email);
    Formular save(Formular form);
    Formular findByUserId(long userId);
}
