package com.buergervereinHSH.BackendProject.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Bitte bestätigen Sie Ihre Email-Adresse anhand des Bestätigungslinks in Ihrem Email Postfach.")
public class AccountNotActivatedException extends RuntimeException {
}
