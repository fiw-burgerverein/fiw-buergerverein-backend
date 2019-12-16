package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.forms.model.Aufwand;

import java.util.List;

public interface AufwandDao {
    //wenn ich das zum List machen wollte??
    List<Aufwand> findByFormId(long formId);
    List<Aufwand> getAllAufwande(long formId);
    Aufwand save(Aufwand aufwand);
}
