package io.cardinality.csvconverter.commons.exception;

import org.springframework.http.HttpStatus;

public class NoDataFoundException extends PositionException{
    public NoDataFoundException(String message) {
        super(message);
    }
    public HttpStatus getStatus(){
        return HttpStatus.NOT_FOUND;
    }
}
