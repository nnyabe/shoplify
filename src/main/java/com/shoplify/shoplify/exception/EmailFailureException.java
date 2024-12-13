package com.shoplify.shoplify.exception;

public class EmailFailureException extends Exception {
    public EmailFailureException() {
        super();
    }

    public EmailFailureException(String message) {
        super(message);
    }
}
