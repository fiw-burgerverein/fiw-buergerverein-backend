package com.buergervereinHSH.BackendProject.forms.controller;

import com.buergervereinHSH.BackendProject.auth.model.User;
import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.forms.dataTransferObject.request.FormDto;
import com.buergervereinHSH.BackendProject.forms.service.FormMailService;
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

    @Autowired
    FormMailService  formMailService;


/*    @Autowired
    private FormMailService formMailService;*/

    @PostMapping("/{userId}/formular")
    public ApiResponse saveForm(@PathVariable("userId") long userId, @Valid @RequestBody FormDto formDto) {
        return formService.saveForm(userId, formDto);
    }

    @RequestMapping("/{userId}/formular/bestaetigen")  //gegen Antrags ID tauschen
    public ApiResponse sendPDFToUser(@PathVariable("userId") long userId) throws MessagingException {
        return formService.sendPDFtoUser(userId);
    }

}
