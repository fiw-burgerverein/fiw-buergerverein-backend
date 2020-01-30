package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.forms.model.Aufwand;
import com.buergervereinHSH.BackendProject.forms.model.Formular;
import org.springframework.data.repository.CrudRepository;

public interface AufwandDao  extends CrudRepository<Aufwand, Long> {

    Aufwand[] findByForm(Formular form);
    Aufwand save(Aufwand aufwand);

}
