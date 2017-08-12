package com.kyepot.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Insufficient balance for withdrawal")
public class InsufficientBalanceException extends RuntimeException {

    private static final long serialVersionUID = -8780126415144380740L;

}
