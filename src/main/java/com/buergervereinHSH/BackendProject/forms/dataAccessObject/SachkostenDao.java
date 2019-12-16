package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;

import java.util.List;

public interface SachkostenDao {
    //wenn ich das zum List machen wollte??
    //Sachkosten findBySachkostenId(long sachkostenId);
    List<Sachkosten> getAllSachkosten(long formId);
}
