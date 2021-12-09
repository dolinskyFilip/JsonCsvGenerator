package io.cardinality.csvconverter.controller;


import io.cardinality.csvconverter.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class PositionControllerImpl implements PositionController {

    @Autowired
    PositionService service;

    @Override
    public ResponseEntity<?> getCsvPositions(String size) {

        try {
            service.getPositions(size);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<?> getCsvPositionsByParameter(String size, List<String> params) {

        try {
            service.getFilteredPositions(size, params);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
