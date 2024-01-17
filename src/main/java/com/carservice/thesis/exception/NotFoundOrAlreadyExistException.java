package com.carservice.thesis.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundOrAlreadyExistException extends RuntimeException {

    public NotFoundOrAlreadyExistException(String message) {
        super(message);
    }
}
