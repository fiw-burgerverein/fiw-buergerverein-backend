package com.buergervereinHSH.BackendProject.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Die eingegebene Passwörter stimmen nicht überein.")
public class PasswordMismatchException  extends RuntimeException {
}
