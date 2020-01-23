package com.buergervereinHSH.BackendProject.forms.controller;

import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.GeschStellenDto;
import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.forms.model.Formular;
import com.buergervereinHSH.BackendProject.forms.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/alleAntraege")
public class GeschStellenController {


    @Autowired
    private FormService formService;

    @PostMapping("/{formId}")
    public ApiResponse changeState(@PathVariable("formId") long formId, @Valid @RequestBody GeschStellenDto geschStellenDto) {
        return formService.changeState(formId, geschStellenDto);
    }

/*    @GetMapping("/test")
    public ApiResponse getAllForms() {
        return formService.getAllForms();
    }*/

}
