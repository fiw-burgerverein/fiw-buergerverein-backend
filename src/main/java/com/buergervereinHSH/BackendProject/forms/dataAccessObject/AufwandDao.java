package com.buergervereinHSH.BackendProject.forms.dataAccessObject;

import com.buergervereinHSH.BackendProject.forms.model.Aufwand;

public interface AufwandDao {
    //wenn ich das zum Set machen wollte??
    Aufwand findByAufwandId(long aufwandId);
}
