package com.buergervereinHSH.BackendProject.forms.model;

import com.buergervereinHSH.BackendProject.auth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Formular")
public class Formular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long formId;
    private LocalDateTime createdAt;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")@JsonIgnore
    private User user;
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private String projektname;
    private String beschreibung;
    private Date startdatum;
    private Date enddatum;
    private String ort;
    private String zielgruppe;
    private int anzteiln;
    private String oeffarb;
    private String oeffarbbeschr;

//    @OneToOne(targetEntity = Sachkosten.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "sachkostenId")
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long sachkostenId;        <-- wir können kein sachkostenId hier generieren weil jede Spalte in der
//                                          Sachkosten-Tabelle sollte ihren eigenen Schlüssel haben; und wir können kein
//                                          Id hier anbinden da es eigtl für jede Spalte einen gibt! Also rufen wir die
//                                          Sachkosten- und Aufwandtabellen per Fremdschlüssel FormId auf.

//    private float sachkostenSum;
//    private float aufwandSum;
    private float gesamtkosten;
    private String anrede;
    private String vorname;
    private String nachname;
    private String einrichtung;
    private String strasse;
    private int hausNr;
    private int plz;
    private String email;
    private int telNr;

    public long getFormId() {   return formId; }
    public void setFormId(long formId) { this.formId = formId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getBeschreibung() { return beschreibung; }
    public void setBeschreibung(String beschreibung) { this.beschreibung = beschreibung; }

    public String getOrt() { return ort; }
    public void setOrt(String ort) { this.ort = ort; }

    public String getZielgruppe() { return zielgruppe; }
    public void setZielgruppe(String zielgruppe) { this.zielgruppe = zielgruppe; }

    public String getProjektname() {
        return projektname;
    }

    public void setProjektname(String projektname) {
        this.projektname = projektname;
    }

    public Date getStartdatum() {
        return startdatum;
    }

    public void setStartdatum(Date startdatum) {
        this.startdatum = startdatum;
    }

    public Date getEnddatum() {
        return enddatum;
    }

    public void setEnddatum(Date enddatum) {
        this.enddatum = enddatum;
    }

    public int getAnzteiln() {
        return anzteiln;
    }

    public void setAnzteiln(int anzteiln) {
        this.anzteiln = anzteiln;
    }

    public String getOeffarb() {
        return oeffarb;
    }

    public void setOeffarb(String oeffarb) {
        this.oeffarb = oeffarb;
    }

    public String getOeffarbbeschr() {
        return oeffarbbeschr;
    }

    public void setOeffarbbeschr(String oeffarbbeschr) {
        this.oeffarbbeschr = oeffarbbeschr;
    }

    public float getGesamtkosten() { return gesamtkosten; }
    public void setGesamtkosten(float gesamtkosten) { this.gesamtkosten = gesamtkosten; }

    //    public float getSachkostenSum() { return sachkostenSum; }
//    public void setSachkostenSum(float sachkostenSum) { this.sachkostenSum = sachkostenSum; }
//
//    public float getAufwandSum() { return aufwandSum; }
//    public void setAufwandSum(float aufwandSum) { this.aufwandSum = aufwandSum; }

    public String getAnrede() { return anrede; }
    public void setAnrede(String anrede) { this.anrede = anrede; }

    public String getVorname() { return vorname; }
    public void setVorname(String vorname) { this.vorname = vorname; }

    public String getNachname() { return nachname; }
    public void setNachname(String nachname) { this.nachname = nachname; }

    public String getEinrichtung() { return einrichtung; }
    public void setEinrichtung(String einrichtung) { this.einrichtung = einrichtung; }

    public String getStrasse() { return strasse; }
    public void setStrasse(String strasse) { this.strasse = strasse; }

    public int getHausNr() { return hausNr; }
    public void setHausNr(int hausNr) { this.hausNr = hausNr; }

    public int getPlz() { return plz; }
    public void setPlz(int plz) { this.plz = plz; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getTelNr() { return telNr; }
    public void setTelNr(int telNr) { this.telNr = telNr; }
}
