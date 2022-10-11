package com.example.usermanagement.exception;

import com.example.usermanagement.enums.ErrorType;
import lombok.Data;

@Data
public class GenericServiceException extends RuntimeException {

    protected final ErrorType error;
    protected final String errorDescription;

    public GenericServiceException(ErrorType error) {
        super(error.getError());
        this.error = error;
        this.errorDescription = error.getError();
    }

    public GenericServiceException(ErrorType error, String errorDescription) {
        super(error.getError());
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error.getError();
    }

}
