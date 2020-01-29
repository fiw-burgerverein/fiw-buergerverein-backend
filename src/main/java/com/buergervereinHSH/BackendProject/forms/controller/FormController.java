package com.buergervereinHSH.BackendProject.forms.controller;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.forms.dataTransferObject.request.FormDto;
import com.buergervereinHSH.BackendProject.forms.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class FormController {

    @Autowired
    private FormService formService;

    @PostMapping("/{userId}/formular")
    public ApiResponse saveForm(@PathVariable("userId") long userId, @Valid @RequestBody FormDto formDto) {
        return formService.saveForm(userId, formDto);
    }

}
