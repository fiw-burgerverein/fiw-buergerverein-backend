package com.buergervereinHSH.BackendProject.forms.dataTransferObject.request;

//import com.buergervereinHSH.BackendProject.forms.model.Antrag;
import com.buergervereinHSH.BackendProject.forms.model.Formular;
import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

public class AntragDto {

    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String projectName;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String beschreibung;
    private Date startDate;
    private Date endDate;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String ort;
    private String zielgruppe;
    private int anzTeilnehmer;
    private String activities;
    private String activitiesBeschreibung;

    /*private String sachzweck;
    private String sachkosten;*/

   /* private String aufwZweck;
    private String sachZweck;*/

    private ArrayList<Sachkosten> sachList;
   // private ArrayList<Sachkosten> sachkostenList;

    public ArrayList<Sachkosten> getSachList() { return sachList; }
    public void setSachkostenList(ArrayList<Sachkosten> sachList) { this.sachList = sachList; }

    private String anrede;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String vorname;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String nachname;
    private String einrichtung;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String strasse;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private int hausNr;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private int plz;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
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

    public String getAnrede() { return anrede; }
    public void setAnrede(String anrede) { this.anrede = anrede; }

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

 /*   public String getSachzweck() { return sachzweck; }
    public void setSachzweck(String sachzweck) { this.sachzweck = sachzweck; }

    public String getSachkosten() { return sachkosten; }
    public void setSachkosten(String sachkosten) { this.sachkosten = sachkosten; }*/

/*    public String getAufwZweck() { return aufwZweck; }
    public void setAufwZweck(String aufwZweck) { this.aufwZweck = aufwZweck; }

    public String getSachZweck() { return sachZweck; }
    public void setSachZweck(String sachZweck) { this.sachZweck = sachZweck; }*/

}
