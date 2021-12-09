package io.cardinality.csvconverter.service;


import java.io.IOException;
import java.util.List;

public interface PositionService {

    void getPositions(String size) throws IOException;

    void getFilteredPositions(String size, List<String> params) throws IOException;
}
