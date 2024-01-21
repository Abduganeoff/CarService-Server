package com.carservice.thesis.exception;

public class WrongInputException extends IllegalStateException{

    public WrongInputException(String message) {
        super(message);
    }
}
