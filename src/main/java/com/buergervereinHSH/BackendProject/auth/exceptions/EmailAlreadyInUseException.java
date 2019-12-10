package com.buergervereinHSH.BackendProject.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Diese Email-Adresse wird von einem anderen Account benutzt.")
public class EmailAlreadyInUseException extends RuntimeException {
}
