package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.forms.model.Formular;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface FormDao extends CrudRepository<Formular, Long> {


    Formular findByFormId(long formId);
    Formular findByEmail(String email);
    Formular save(Formular form);
    List<Formular> getFormularByUser(User user);

    @Query("select formId from Formular")
    List<Long> getAllIds();

    @Query("select f from Formular f")
    List<Formular> getAllForms();

}
