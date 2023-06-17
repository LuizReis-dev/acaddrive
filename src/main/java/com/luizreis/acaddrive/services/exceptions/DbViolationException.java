package com.luizreis.acaddrive.services.exceptions;

public class DbViolationException extends RuntimeException{
    public DbViolationException(String message) {
        super(message);
    }
}
