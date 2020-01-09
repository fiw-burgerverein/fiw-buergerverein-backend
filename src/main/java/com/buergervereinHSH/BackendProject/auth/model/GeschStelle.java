package com.buergervereinHSH.BackendProject.auth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class GeschStelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long geschId;
    private String name;
    @NotNull
    private String email;
    private String telefon;
    private String strasse;
    private String hausnummer;
    private int plz;
    private String ort;

    public long getGeschId() { return geschId; }
    public void setGeschId(long geschId) { this.geschId = geschId;}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getStrasse() {return strasse;}
    public void setStrasse(String strasse) {this.strasse = strasse;}

    public String getHausnummer() {return hausnummer;}
    public void setHausnummer(String hausnummer) { this.hausnummer = hausnummer; }

    public int getPlz() { return plz; }
    public void setPlz(int plz) { this.plz = plz; }

    public String getOrt() { return ort; }
    public void setOrt(String ort) { this.ort = ort; }
}
