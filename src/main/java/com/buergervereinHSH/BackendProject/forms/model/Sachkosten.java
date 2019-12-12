package com.buergervereinHSH.BackendProject.forms.model;

import javax.persistence.*;

@Entity
@Table(name = "Sachkosten")
public class Sachkosten {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sachkostenId;
}
