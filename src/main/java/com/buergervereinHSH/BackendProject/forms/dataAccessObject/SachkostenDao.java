package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;

public interface SachkostenDao {
    //wenn ich das zum Set machen wollte??
    Sachkosten findBySachkostenId(long sachkostenId);
}
