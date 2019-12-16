package com.buergervereinHSH.BackendProject.forms.model;

import com.buergervereinHSH.BackendProject.auth.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Formular")
public class Formular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long formId;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private LocalDateTime createdAt;    //welcher Datentyp?
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private long userId;

    private String projectName;
    private String beschreibung;
    private Date startDate;
    private Date endDate;
    private String ort;
    private String zielgruppe;
    private int anzTeilnehmer;
    private enum activities { JA, NEIN };
    private String activitiesBeschreibung;

//    @OneToOne(targetEntity = Sachkosten.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "sachkostenId")
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long sachkostenId;        <-- wir können kein sachkostenId hier generieren weil jede Spalte in der
//                                          Sachkosten-Tabelle sollte ihren eigenen Schlüssel haben; und wir können kein
//                                          Id hier anbinden da es eigtl für jede Spalte einen gibt! Also rufen wir die
//                                          Sachkosten- und Aufwandtabellen per Fremdschlüssel FormId auf.
    private int sachkostenSum;
    private int aufwandSum;

    private enum anrede { FRAU, HERR, DIVERSE };
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

    public long getUserId() { return userId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getBeschreibung() { return beschreibung; }
    public void setBeschreibung(String beschreibung) { this.beschreibung = beschreibung; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getOrt() { return ort; }
    public void setOrt(String ort) { this.ort = ort; }

    public String getZielgruppe() { return zielgruppe; }
    public void setZielgruppe(String zielgruppe) { this.zielgruppe = zielgruppe; }

    public int getAnzTeilnehmer() { return anzTeilnehmer; }
    public void setAnzTeilnehmer(int anzTeilnehmer) { this.anzTeilnehmer = anzTeilnehmer; }

    public String getActivitiesBeschreibung() { return activitiesBeschreibung; }
    public void setActivitiesBeschreibung(String activitiesBeschreibung)
    { this.activitiesBeschreibung = activitiesBeschreibung; }

    public int getSachkostenSum() { return sachkostenSum; }
    public void setSachkostenSum(int sachkostenSum) { this.sachkostenSum = sachkostenSum; }

    public int getAufwandSum() { return aufwandSum; }
    public void setAufwandSum(int aufwandSum) { this.aufwandSum = aufwandSum; }

    public String getVorname() { return vorname; }
    public void setVorname(String vorname) { this.vorname = vorname; }

    public String getNachname() { return nachname; }
    public void setNachname(String nachname) { nachname = nachname; }

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
