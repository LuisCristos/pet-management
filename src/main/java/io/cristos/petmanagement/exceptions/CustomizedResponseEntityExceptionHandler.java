package io.cristos.petmanagement.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders httpHeaders,
            HttpStatusCode statusCode,
            WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "Total Errors: " + ex.getErrorCount() + " First Error: " + ex.getFieldError().getDefaultMessage(),
                request.getDescription(false),
                httpStatus);

        return new ResponseEntity<>(errorDetails, httpStatus);
    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) throws Exception {

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false),
                httpStatus);

        return new ResponseEntity<>(errorDetails, httpStatus);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) throws Exception {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false),
                httpStatus);

        return new ResponseEntity<>(errorDetails, httpStatus);
    }
}
