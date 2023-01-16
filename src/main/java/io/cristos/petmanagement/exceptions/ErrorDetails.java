package io.cristos.petmanagement.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDetails {

    private final LocalDateTime localDateTime;
    private final String message;
    private final String details;
    private final HttpStatus httpStatus;

    public ErrorDetails(LocalDateTime localDateTime, String message, String details, HttpStatus httpStatus) {
        this.localDateTime = localDateTime;
        this.message = message;
        this.details = details;
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
