package com.buergervereinHSH.BackendProject.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Die eingegebene Email-Adressen stimmen nicht überein.")
public class EmailMismatchException extends RuntimeException {
}
