package io.cardinality.csvconverter.service;

import java.io.IOException;
import java.util.List;

public interface PositionService {

    byte[] getPositions(String size) throws IOException;

    byte[] getFilteredPositions(String size, List<String> params) throws IOException;
}
