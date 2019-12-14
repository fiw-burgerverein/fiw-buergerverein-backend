package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.forms.model.Aufwand;

import java.util.List;

public interface AufwandDao {
    //wenn ich das zum List machen wollte??
    //Aufwand findByAufwandId(long aufwandId);
    List<Aufwand> getAllAufwande(long aufwandId);
}
