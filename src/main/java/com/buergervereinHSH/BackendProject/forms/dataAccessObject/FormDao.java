package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.forms.model.Formular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FormDao extends CrudRepository<Formular, Long> {


    Formular findByFormId(long formId);
    Formular findByEmail(String email);
    Formular save(Formular form);

   // Formular[] findByUser(User user);


/*    @Query(value="SELECT form_id from formular")
    List<Long> getAllIds();

    Iterable<Formular> findAllByFormId(Iterable<Long> formId);*/
}
