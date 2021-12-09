package io.cardinality.jsonapp.web.advice;

import lombok.Data;

@Data
public class ErrorResponse {

    public String errorId;
    public String message;
    public String errorCode;
}
