package de.futurecompany.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FutureBusinessException extends ResponseStatusException {

    public FutureBusinessException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
    }
}
