package com.buergervereinHSH.BackendProject.forms.service;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.forms.dataTransferObject.request.AntragDto;
//import com.buergervereinHSH.BackendProject.forms.model.Antrag;

public interface AntragService {

    ApiResponse saveForm(AntragDto antragDto);

}
