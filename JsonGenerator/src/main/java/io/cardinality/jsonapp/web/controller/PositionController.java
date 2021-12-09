package io.cardinality.jsonapp.web.controller;

import io.cardinality.jsonapp.model.FlatPosition;
import io.cardinality.jsonapp.model.Position;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PositionController {

    @PostMapping(path="/generate/json/{size}", produces = "application/json")
    ResponseEntity<List<Position>> getPositions(@PathVariable String size);

    @PostMapping(path="/generate/json/flat/{size}", produces = "application/json")
    ResponseEntity<List<FlatPosition>> getFlatPositionsFiltered(@PathVariable String size);

    @PostMapping(path="/generate/json/flat/{size}/filter", produces = "application/json")
    ResponseEntity<List<FlatPosition>> getFlatPositionsIndividualFiltered(@PathVariable String size,
                                                         @RequestParam(value="property")List<String> properties) ;
}
