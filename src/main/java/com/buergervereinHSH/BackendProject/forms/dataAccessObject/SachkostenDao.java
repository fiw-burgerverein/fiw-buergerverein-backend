package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;

import java.util.List;

public interface SachkostenDao {
    //wenn ich das zum List machen wollte??
    List<Sachkosten> findByFormId(long formId);
    List<Sachkosten> getAllSachkosten(long formId);
    Sachkosten save(Sachkosten sachkosten);
}
