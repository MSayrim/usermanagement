package com.example.usermanagement.handler;


import com.example.usermanagement.dto.request.GenericServiceResult;
import com.example.usermanagement.enums.ErrorType;
import com.example.usermanagement.enums.ResponseStatus;
import com.example.usermanagement.exception.GenericServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({ GenericServiceException.class })
    public ResponseEntity<GenericServiceResult> handleGenericServiceException(GenericServiceException e) {
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new GenericServiceResult(ResponseStatus.HANDLED_EXCEPTION, ResponseStatus.HANDLED_EXCEPTION.getResultCode(), e.getError(), e.getErrorDescription()), HttpStatus.OK);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<GenericServiceResult> handleRuntimeException(RuntimeException e) {
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new GenericServiceResult(ResponseStatus.ERROR, ResponseStatus.HANDLED_EXCEPTION.getResultCode(), ErrorType.RUNTIME_EXCEPTION.getError(), e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler({ AccessDeniedException.class})
    public ResponseEntity<GenericServiceResult> handleAccessDenied(Exception e) {
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new GenericServiceResult(ResponseStatus.ERROR, ResponseStatus.HANDLED_EXCEPTION.getResultCode(), ErrorType.ACCESS_DENIED.getError(), e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<GenericServiceResult> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        final List<String> subErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> subErrors.add(e.getField() + ": " + e.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(e -> subErrors.add(e.getObjectName() + ": " + e.getDefaultMessage()));

        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new GenericServiceResult(ResponseStatus.ERROR, ResponseStatus.HANDLED_EXCEPTION.getResultCode(), ErrorType.METHOD_ARGUMENT_NOT_VALID.getError(), subErrors), HttpStatus.OK);
    }




}