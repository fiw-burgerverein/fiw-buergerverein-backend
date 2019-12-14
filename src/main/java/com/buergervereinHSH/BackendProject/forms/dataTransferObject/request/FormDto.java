package com.buergervereinHSH.BackendProject.forms.dataTransferObject.request;

import com.buergervereinHSH.BackendProject.forms.model.Aufwand;
import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class FormDto {

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
}
