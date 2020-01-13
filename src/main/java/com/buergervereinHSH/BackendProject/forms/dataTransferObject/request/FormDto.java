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
    private String projektname;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String beschreibung;
    @NotNull
    private Date startdatum;
    @NotNull
    private Date enddatum;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String ort;
    private String zielgruppe;
    @NotNull
    private int anzteiln;
    @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String oeffarb;
    private String oeffarbbeschr;

//    private Sachkosten[] sachkostenArray;
//    private Aufwand[] aufwandArray;
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
    private int hausnr;
    @NotNull
    private int plz;
    @Email @NotNull @NotBlank @NotEmpty(message = "Sie müssen dieses Feld ausfüllen.")
    private String email;
    private int telnr;

//  Getters and Setters
    public String getProjektname() { return projektname; }
    public void setProjektname(String projektname) { this.projektname = projektname; }

    public String getBeschreibung() { return beschreibung; }
    public void setBeschreibung(String beschreibung) { this.beschreibung = beschreibung; }

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

    public String getOrt() { return ort; }
    public void setOrt(String ort) { this.ort = ort; }

    public String getZielgruppe() { return zielgruppe; }
    public void setZielgruppe(String zielgruppe) { this.zielgruppe = zielgruppe; }

//    public Sachkosten[] getSachkostenArray() { return sachkostenArray; }
//    public void setSachkostenArray(Sachkosten[] sachkostenArray) { this.sachkostenArray = sachkostenArray; }
//
//    public Aufwand[] getAufwandArray() { return aufwandArray; }
//    public void setAufwandArray(Aufwand[] aufwandArray) { this.aufwandArray = aufwandArray; }

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

    public int getHausnr() { return hausnr; }
    public void setHausnr(int hausNr) { this.hausnr = hausnr; }

    public int getPlz() { return plz; }
    public void setPlz(int plz) { this.plz = plz; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getTelnr() { return telnr; }
    public void setTelnr(int telNr) { this.telnr = telnr; }

}
