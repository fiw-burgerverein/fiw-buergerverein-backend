package com.buergervereinHSH.BackendProject.forms.controller;

import com.buergervereinHSH.BackendProject.auth.dataTransferObject.request.GeschStellenDto;
import com.buergervereinHSH.BackendProject.auth.model.GeschStelle;
import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.forms.dataTransferObject.request.FormDto;
import com.buergervereinHSH.BackendProject.forms.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class FormController {

    @Autowired
    private FormService formService;


    @PostMapping("/{userId}/formular")
    public ApiResponse saveForm(@PathVariable("userId") long userId, @Valid @RequestBody FormDto formDto) {
        return formService.saveForm(userId, formDto);
    }

    @RequestMapping("/{userId}/formular/bestaetigen")  //hier nur zu testzwecken; wird wenn dann später Teil der saveForm methode
    public ApiResponse sendPDFToUser(@PathVariable("userId") long userId) throws MessagingException {
        return formService.sendPDFtoUser(userId);
    }
}
