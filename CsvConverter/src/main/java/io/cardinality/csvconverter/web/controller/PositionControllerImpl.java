package io.cardinality.csvconverter.web.controller;


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
            byte[] result = service.getPositions(size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getCsvPositionsByParameter(String size, List<String> params) {
        try {
            byte[] result = service.getFilteredPositions(size, params);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
