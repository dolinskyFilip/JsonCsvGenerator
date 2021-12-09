package io.cardinality.csvconverter.integration;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class IntegrationException extends RuntimeException{

    protected final String  errorCode;
    protected final String errorId;
    protected  HttpStatus status;

    public IntegrationException(String message, String errorCode, String errorId) {
        super(message);
        this.errorCode = errorCode;
        this.errorId = errorId;
    }

    public IntegrationException(String message, String errorCode, String errorId, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.errorId = errorId;
        this.status = status;
    }
}
