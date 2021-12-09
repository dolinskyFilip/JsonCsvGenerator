package io.cardinality.jsonapp.commons.service;

import io.cardinality.jsonapp.model.FlatPosition;
import io.cardinality.jsonapp.model.Position;

import java.util.List;

public interface PositionService {

    List<Position> generatePositions(String size);

    List<FlatPosition> generateFlatPositions(String size);
}
