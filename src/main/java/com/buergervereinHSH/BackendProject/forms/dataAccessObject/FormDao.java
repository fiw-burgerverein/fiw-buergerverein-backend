package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.forms.dataTransferObject.request.AllFormsDto;
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

    @Query("select formId from Formular")
    List<Long> getAllIds();

    @Query("select f from Formular f")
    List<Formular> getAllForms();

/*
    @Query("select f.formId, f.createdAt, f.projectName, f.status from Formular f")
    List<AllFormsDto> getAllForms();
*/

 /*   List<AllFormsDto> getAllForms();*/


   /* @Query("select formId, createdAt, projectName, status from Formular")
    List<AllFormsDto> getAllForms();*/
    //Iterable<AllFormsDto> getAllForms(Iterable<AllFormsDto> formId);
}
