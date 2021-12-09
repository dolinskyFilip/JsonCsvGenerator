package io.cardinality.jsonapp.commons.exception;

import org.springframework.http.HttpStatus;

public class PositionException  extends RuntimeException{

    protected String errorCode;

    protected String errorId;

    public PositionException(String message) {
       super(message);
    }

    public PositionException(String message,Throwable cause) {
        super(message,cause);
    }

    public PositionException(String message, Throwable cause, String errorCode, String errorId) {
        super(message,cause);
        this.errorCode = errorCode;
        this.errorId=errorId;
    }

    public PositionException(String message, String errorCode, String errorId) {
        super(message);
        this.errorCode = errorCode;
        this.errorId=errorId;
    }

    public HttpStatus getStatus(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }



}
