package io.cristos.petmanagement.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ApiError {
    private final LocalDateTime timeStamp;
    private final HttpStatus httpStatus;
    private final String message;
    private final List<String> errors;


    public ApiError(LocalDateTime timeStamp, HttpStatus httpStatus, String message, List<String> errors) {
        super();
        this.timeStamp = timeStamp;
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(LocalDateTime timeStamp, HttpStatus httpStatus, String message, String error) {
        super();
        this.timeStamp = timeStamp;
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = Arrays.asList(error);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
