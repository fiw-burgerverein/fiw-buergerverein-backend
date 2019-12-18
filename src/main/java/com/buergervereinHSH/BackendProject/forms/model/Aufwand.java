package com.buergervereinHSH.BackendProject.forms.model;

import javax.persistence.*;

@Entity
@Table(name = "Aufwand")
public class Aufwand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aufwandId;
    @ManyToOne(targetEntity = Formular.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "formId")
    private Formular form;

    private String Zweck;
    private float cost;

    public long getAufwandId() { return aufwandId; }
    public void setAufwandId(long aufwandId) { this.aufwandId = aufwandId; }

    public Formular getForm() { return form; }
    public void setForm(Formular form) { this.form = form; }

    public String getZweck() { return Zweck; }
    public void setZweck(String zweck) { Zweck = zweck; }

    public float getCost() { return cost; }
    public void setCost(float cost) { this.cost = cost; }

}
