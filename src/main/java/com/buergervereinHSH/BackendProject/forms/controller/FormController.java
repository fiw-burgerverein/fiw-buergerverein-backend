package com.buergervereinHSH.BackendProject.forms.controller;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.forms.dataTransferObject.request.FormDto;
import com.buergervereinHSH.BackendProject.forms.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class FormController {

    @Autowired
    private FormService formService;


  /*  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/{userId}/formular")
    public ApiResponse saveForm(@PathVariable("userId") long userId, @Valid @RequestBody FormDto formDto) {
        return formService.saveForm(userId, formDto);
    }*/

   @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
   @PostMapping("/formular")
   public ApiResponse saveForm(HttpServletRequest request, @Valid @RequestBody FormDto formDto) {
       return formService.saveForm(request, formDto);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping("/{userId}/formular/bestaetigen")  //hier nur zu testzwecken; wird wenn dann später Teil der saveForm methode
    public ApiResponse sendPDFToUser(@PathVariable("userId") long userId) throws MessagingException {
        return formService.sendPDFtoUser(userId);
    }

/*    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("test/getUserid")
    *//*  public Long getUserIdfromToken(@RequestHeader (name="Authorization") String token) {*//*
    public Long getUserIdfromToken(HttpServletRequest request) {
        return formService.getUserIdfromToken(request);
    }*/

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("alleAntraegeUserin")
    public ResponseEntity<ApiResponse>getAllFormsOfUser(HttpServletRequest request) {
        return formService.getAllFormsOfUser(request);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{formId}")
    public ApiResponse getSingleForm(@PathVariable("formId") long formId) {
        return formService.getSingleForm(formId);
    }
}
