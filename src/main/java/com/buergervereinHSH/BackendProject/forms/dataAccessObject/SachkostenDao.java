package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.forms.model.Formular;
import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;
import org.springframework.data.repository.CrudRepository;

public interface SachkostenDao  extends CrudRepository<Sachkosten, Long> {

    Sachkosten[] findByForm(Formular form);
    Sachkosten save(Sachkosten sachkosten);

}
