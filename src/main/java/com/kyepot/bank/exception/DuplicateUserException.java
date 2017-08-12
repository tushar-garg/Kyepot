package com.kyepot.bank.exception;

public class DuplicateUserException extends RuntimeException {
    private static final long serialVersionUID = -5457238752994074041L;

    public DuplicateUserException(String message) {
        super(message);
    }

}
