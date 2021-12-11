package io.cardinality.csvconverter.integration;

import io.cardinality.csvconverter.model.FlatPosition;

import java.util.List;

public interface JsonGeneratorClient {
    List<FlatPosition> invokeJsonGeneratorService(String size);
}
