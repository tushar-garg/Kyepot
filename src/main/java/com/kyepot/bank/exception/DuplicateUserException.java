package com.kyepot.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "User with same name and email already exists")
public class DuplicateUserException extends RuntimeException {
    private static final long serialVersionUID = -5457238752994074041L;

}
