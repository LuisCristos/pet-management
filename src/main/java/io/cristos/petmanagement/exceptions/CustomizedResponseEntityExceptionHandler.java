package io.cristos.petmanagement.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
//@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders httpHeaders,
            HttpStatusCode statusCode,
            WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        List<String> errorsList = new ArrayList<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorsList.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
            errorsList.add(objectError.getObjectName() + ": " + objectError.getDefaultMessage());
        }

        return buildResponseEntity(new ApiError(LocalDateTime.now(), httpStatus, ex.getLocalizedMessage(), errorsList));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        String error = ex.getMessage();

        return buildResponseEntity(new ApiError(LocalDateTime.now(), httpStatus, ex.getLocalizedMessage(), error));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) throws Exception {

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        String error = ex.getMessage();

        return buildResponseEntity(new ApiError(LocalDateTime.now(), httpStatus, ex.getLocalizedMessage(), error));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) throws Exception {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        String error = ex.getMessage();

        return buildResponseEntity(new ApiError(LocalDateTime.now(), httpStatus, ex.getLocalizedMessage(), error));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        List<String> errorsList = new ArrayList<>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errorsList.add(violation.getRootBeanClass().getName()
                    + " " + violation.getPropertyPath()
                    + ": " + violation.getMessage());
        }

        return buildResponseEntity(new ApiError(LocalDateTime.now(), httpStatus, ex.getLocalizedMessage(), errorsList));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        return buildResponseEntity(new ApiError(LocalDateTime.now(), httpStatus, ex.getLocalizedMessage(), error));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Object> handleUnexpectedTypeException(
            UnexpectedTypeException ex, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        String error = ex.getMessage();

        return buildResponseEntity(new ApiError(LocalDateTime.now(), httpStatus, ex.getLocalizedMessage(), error));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

}
