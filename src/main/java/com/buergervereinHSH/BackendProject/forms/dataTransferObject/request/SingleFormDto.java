package com.buergervereinHSH.BackendProject.forms.dataTransferObject.request;

import com.buergervereinHSH.BackendProject.forms.model.Aufwand;
import com.buergervereinHSH.BackendProject.forms.model.Formular;
import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;
import org.springframework.stereotype.Component;

@Component
public class SingleFormDto {

    private Formular formular;
    private Sachkosten[] sachkostenArray;
    private Aufwand[] aufwandArray;

    public void setSachkostenArray(Sachkosten[] sachkostenArray) { this.sachkostenArray = sachkostenArray; }

    public void setAufwandArray(Aufwand[] aufwandArray) { this.aufwandArray = aufwandArray; }

    public Formular getFormular() { return formular; }
    public void setFormular(Formular formular) { this.formular = formular; }


}
