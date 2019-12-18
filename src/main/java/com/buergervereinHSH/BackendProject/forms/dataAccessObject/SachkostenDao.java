package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SachkostenDao  extends CrudRepository<Sachkosten, Long> {

//    Sachkosten[] findByFormId(long formId);
    Sachkosten save(Sachkosten sachkosten);
}
