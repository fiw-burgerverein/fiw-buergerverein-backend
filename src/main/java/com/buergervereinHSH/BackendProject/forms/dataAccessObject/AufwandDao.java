package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.forms.model.Aufwand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AufwandDao  extends CrudRepository<Aufwand, Long> {
    List<Aufwand> findByFormId(long formId);
    Aufwand save(Aufwand aufwand);

}
