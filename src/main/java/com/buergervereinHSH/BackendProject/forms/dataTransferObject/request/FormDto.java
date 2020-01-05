package com.buergervereinHSH.BackendProject.forms.dataTransferObject.request;

import com.buergervereinHSH.BackendProject.forms.model.Aufwand;
import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class FormDto {

    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String projectName;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String beschreibung;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String ort;
    private String zielgruppe;
    @NotNull
    private int anzTeilnehmer;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String activities;
    private String activitiesBeschreibung;

    private Sachkosten[] sachkostenArray;
    private Aufwand[] aufwandArray;
//    auch eine Möglichkeit
//    private ArrayList<Sachkosten> sachkostenList;
//    private ArrayList<Aufwand> aufwandList;

    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String anrede;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String vorname;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String nachname;
    private String einrichtung;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String strasse;
    @NotNull
    private int hausNr;
    @NotNull
    private int plz;
    @Email @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String email;
    private int telNr;

//  Getters and Setters
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

    public String getActivities() { return activities; }
    public void setActivities(String activities) { this.activities = activities; }

    public String getActivitiesBeschreibung() { return activitiesBeschreibung; }
    public void setActivitiesBeschreibung(String activitiesBeschreibung) { this.activitiesBeschreibung = activitiesBeschreibung; }

    public Sachkosten[] getSachkostenArray() { return sachkostenArray; }
    public void setSachkostenArray(Sachkosten[] sachkostenArray) { this.sachkostenArray = sachkostenArray; }

    public Aufwand[] getAufwandArray() { return aufwandArray; }
    public void setAufwandArray(Aufwand[] aufwandArray) { this.aufwandArray = aufwandArray; }

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
