package com.kyepot.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Account doesn't exists")
public class InvalidAccountNumberException extends RuntimeException {

    private static final long serialVersionUID = 4737576789518318848L;

}
