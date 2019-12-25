/*
package com.buergervereinHSH.BackendProject.forms.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import com.buergervereinHSH.BackendProject.auth.model.User;

@Entity
@Table(name="formular")
@SecondaryTables( {
        @SecondaryTable(name = "sachkosten", pkJoinColumns = @PrimaryKeyJoinColumn(name = "formId")),
        @SecondaryTable(name = "aufwand", pkJoinColumns = @PrimaryKeyJoinColumn(name = "formId"))
})
public class Antrag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long formId;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private LocalDateTime createdAt;
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)  //nicht OneToOne!
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
    private enum anrede { FRAU, HERR, DIVERSE };    //Lieber extra Klasse, weil enum?
    private String vorname;
    private String nachname;
    private String einrichtung;
    private String strasse;
    private int hausNr;
    private int plz;
    private String email;
    private int telNr;

    //wenn nicht primäre Tabelle: Tabelle muss mit angegeben werden

    @Column(table = "sachkosten")
    //private String sachzweck;  //List? Doch eigene Models?
    private ArrayList<Sachkosten> sachzweckList;
    @Column (table="sachkosten")
    //private float sachkosten; //List?
    private ArrayList<Sachkosten> sachkostenList;

    public ArrayList<Sachkosten> getSachkostenList() { return sachkostenList; }
    public void setSachkostenList(ArrayList<Sachkosten> sachkostenList) { this.sachkostenList = sachkostenList; }

    */
/*@Column(table = "aufwand")
    private String aufwZweck; //List?
    @Column (table="aufwand")
    private float aufwKosten; //List?*//*


    private int sachkostenSum;
    private int aufwandSum;

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

    //
   */
/* public String getSachzweck() { return sachzweck; }
    public void setsachZweck(String zweck) { this.sachzweck = zweck; }

    public float getSachkosten() { return sachkosten; }
    public void setSachkosten(float kosten) { this.sachkosten = kosten; }*//*


    //
  */
/*  public String getAufwZweck() { return aufwZweck; }
    public void setAufwZweck(String zweck) { this.aufwZweck = zweck; }

    public float getAufwKosten() { return aufwKosten; }
    public void setAufwKosten(float kosten) { this.aufwKosten = kosten; }*//*

}
*/
