package com.carservice.thesis.exception;

public class AlreadyAssignedException extends IllegalStateException {
    public AlreadyAssignedException(String message) {
        super(message);
    }
}
