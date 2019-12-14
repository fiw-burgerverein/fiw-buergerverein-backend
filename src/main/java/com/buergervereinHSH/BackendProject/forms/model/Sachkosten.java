package com.buergervereinHSH.BackendProject.forms.model;

import javax.persistence.*;

@Entity
@Table(name = "Sachkosten")
public class Sachkosten {
    @Id
    @OneToOne(targetEntity = Formular.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "sachkostenId")
    private long sachkostenId;
    private String Zweck;
    private float cost;

    public long getSachkostenId() { return sachkostenId; }
    public void setSachkostenId(long sachkostenId) { this.sachkostenId = sachkostenId; }

    public String getZweck() { return Zweck; }
    public void setZweck(String zweck) { Zweck = zweck; }

    public float getCost() { return cost; }
    public void setCost(float cost) { this.cost = cost; }
}
