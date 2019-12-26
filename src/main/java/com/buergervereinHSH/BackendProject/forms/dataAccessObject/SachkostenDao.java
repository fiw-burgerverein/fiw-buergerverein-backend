package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface SachkostenDao extends CrudRepository {

/*    //wenn ich das zum List machen wollte??
    //ArrayList<Sachkosten> findByFormId(long formId);
    ArrayList<Sachkosten> getAllSachkosten(long formId);*/
    Sachkosten save(Sachkosten sachkosten);
    Sachkosten findByFormId(long formId);
}
