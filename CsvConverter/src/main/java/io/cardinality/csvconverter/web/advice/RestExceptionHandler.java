package io.cardinality.csvconverter.web.advice;

import io.cardinality.csvconverter.commons.exception.PositionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value= PositionException.class)
    public ResponseEntity<?> handleException(PositionException exception){
        var errorResponse= new ErrorResponse();

        errorResponse.setMessage(exception.getMessage());

        return ResponseEntity.status(exception.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException exception){
        var errorResponse= new ErrorResponse();

        errorResponse.setMessage("Validation Error");

        log.error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value= RuntimeException.class)
    public ResponseEntity<?> handleValidationException(RuntimeException exception){
        var errorResponse= new ErrorResponse();

        errorResponse.setMessage("Internal exception");

        log.error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
