package io.cardinality.jsonapp.web.controller;

import io.cardinality.jsonapp.commons.service.PositionService;
import io.cardinality.jsonapp.model.FlatPosition;
import io.cardinality.jsonapp.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PositionControllerImpl implements PositionController{

    @Autowired
    PositionService service;

    @Override
    public ResponseEntity<List<Position>> getPositions(String size) {
       List<Position> response= service.generatePositions(size);

       return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FlatPosition>> getFlatPositionsFiltered(String size) {
        List<FlatPosition> response= service.generateFlatPositions(size);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FlatPosition>> getFlatPositionsIndividualFiltered(String size, List<String> properties) {
        return null;
    }


}
