package io.cardinality.csvconverter.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PositionController {
    @GetMapping(path = "/generate/csv/{size}", produces = "text/csv")
    ResponseEntity<?> getCsvPositions(@PathVariable String size);

    @GetMapping (path = "/generate/csv/{size}/filter",produces = "text/csv")
    ResponseEntity<?> getCsvPositionsByParameter(@PathVariable String size,
                                                 @RequestParam(name = "property") List<String> params);
}

