package io.cardinality.csvconverter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PositionController {

    @PostMapping(path="/generate/csv/{size}", produces = "text/csv")
    ResponseEntity<?> getCsvPositions(@PathVariable String size);

    @PostMapping(path = "/generate/csv/{size}/filter")
    ResponseEntity<?> getCsvPositionsByParameter(@PathVariable String size,
                                                 @RequestParam(name="property") List<String> params);
}
