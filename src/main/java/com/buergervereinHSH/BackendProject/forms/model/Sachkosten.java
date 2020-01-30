package com.buergervereinHSH.BackendProject.forms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Sachkosten")
public class Sachkosten {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sachkostenId;
    @ManyToOne(targetEntity = Formular.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "formId")@JsonIgnore
    private Formular form;

    private String Zweck;
    private float cost;

    public long getSachkostenId() { return sachkostenId; }
    public void setSachkostenId(long sachkostenId) { this.sachkostenId = sachkostenId; }

    public Formular getForm() { return form; }
    public void setForm(Formular form) { this.form = form; }

    public String getZweck() { return Zweck; }
    public void setZweck(String zweck) { Zweck = zweck; }

    public float getCost() { return cost; }
    public void setCost(float cost) { this.cost = cost; }
}
