package com.buergervereinHSH.BackendProject.forms.model;

import javax.persistence.*;

@Entity
@Table(name = "Aufwand")
public class Aufwand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aufwandId;
}
