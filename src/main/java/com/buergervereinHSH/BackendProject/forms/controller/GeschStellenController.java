package com.buergervereinHSH.BackendProject.forms.controller;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.forms.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/alleAntraege")
public class GeschStellenController {


    @Autowired
    private FormService formService;

    @PreAuthorize("hasRole('GS') or hasRole('ADMIN')")
    @GetMapping("/")
    public ApiResponse getAllForms() {
        return formService.getAllForms();
    }


    @PreAuthorize("hasRole('GS') or hasRole('ADMIN')")
    @PostMapping("/{formId}")
    public ResponseEntity<?> changeState(@PathVariable("formId") long formId, @Valid @RequestBody int statusInt) {
        return formService.changeState(formId, statusInt);
    }

    @PreAuthorize("hasRole('GS') or hasRole('ADMIN')")
    @GetMapping("/{formId}")
    public ApiResponse getSingleForm(@PathVariable("formId") long formId) {
        return formService.getSingleForm(formId);
    }
}


