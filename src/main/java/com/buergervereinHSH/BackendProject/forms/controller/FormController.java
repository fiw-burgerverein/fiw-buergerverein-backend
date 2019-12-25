package com.buergervereinHSH.BackendProject.forms.controller;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.forms.dataTransferObject.request.AntragDto;
import com.buergervereinHSH.BackendProject.forms.service.AntragService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class FormController {

    @Autowired
    private AntragService antragService;

    @PostMapping("/formular")
    public ApiResponse saveForm(@Valid @RequestBody AntragDto antragDto) {
        return antragService.saveForm(antragDto);
    }
}