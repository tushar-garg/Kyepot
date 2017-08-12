package com.kyepot.bank.exception;

public class InvalidAccountNumberException extends RuntimeException {

    private static final long serialVersionUID = 4737576789518318848L;

    public InvalidAccountNumberException(String message) {
        super(message);
    }

}
