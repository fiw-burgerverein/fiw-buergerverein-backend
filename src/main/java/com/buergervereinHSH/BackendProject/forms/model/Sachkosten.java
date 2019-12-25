package com.buergervereinHSH.BackendProject.forms.model;

import javax.persistence.*;

@Entity
@Table(name = "Sachkosten")

public class Sachkosten {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sachkostenId;
    @ManyToOne(targetEntity = Formular.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "formId")
    private Formular formular;
   // private long formId;

    private String zweck;
    private float kosten;

    public long getSachkostenId() { return sachkostenId; }
    public void setSachkostenId(long sachkostenId) { this.sachkostenId = sachkostenId; }

    /*public long getFormId() { return formId; }
    public void setFormId(long formId) { this.formId = formId; }*/

    public String getZweck() { return zweck; }
    public void setZweck(String zweck) { this.zweck = zweck; }

    public float getKosten() { return kosten; }
    public void setKosten(float kosten) { this.kosten = kosten; }
}
