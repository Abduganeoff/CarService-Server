package com.carservice.thesis.exception;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
