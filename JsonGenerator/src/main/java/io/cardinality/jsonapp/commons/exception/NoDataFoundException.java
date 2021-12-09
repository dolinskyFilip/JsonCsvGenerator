package io.cardinality.jsonapp.commons.exception;

import org.springframework.http.HttpStatus;

public class NoDataFoundException extends PositionException{
    public NoDataFoundException(String message) {
        super(message);
    }
    @Override
    public HttpStatus getStatus(){
        return HttpStatus.BAD_REQUEST;
    }
}
