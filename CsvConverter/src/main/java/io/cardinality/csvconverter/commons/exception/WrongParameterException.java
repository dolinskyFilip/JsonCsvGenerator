package io.cardinality.csvconverter.commons.exception;

import org.springframework.http.HttpStatus;

public class WrongParameterException extends PositionException {
    public WrongParameterException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus(){
        return HttpStatus.BAD_REQUEST;
    }
}
