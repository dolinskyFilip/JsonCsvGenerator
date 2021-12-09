package io.cardinality.csvconverter.integration;

import io.cardinality.csvconverter.model.FlatPosition;

import java.util.List;

public interface JsonGeneratorClient {
    String ERROR_ID="errorId";
    String ERROR_CODE="errorCode";
    String ERROR_MESSAGE="message";
    List<FlatPosition> invokeJsonGeneratorService(String size);
}
