package io.cardinality.csvconverter.service;


import io.cardinality.csvconverter.commons.converter.JsonToCsVConverter;
import io.cardinality.csvconverter.commons.exception.WrongParameterException;
import io.cardinality.csvconverter.integration.JsonGeneratorClient;
import io.cardinality.csvconverter.model.FlatPosition;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    JsonGeneratorClient client;

    @Autowired
    JsonToCsVConverter converter;

    @Override
    public void getPositions(String size) throws IOException {
        List<FlatPosition> positions = client.invokeJsonGeneratorService(size);
        List<String> propertiesToInclude = List.of("_type", "_id", "name", "type", "latitude", "longitude");
        converter.convert(positions, propertiesToInclude);
    }

    @Override
    public void getFilteredPositions(String size, List<String> params) throws IOException {

        if (size == null || !size.matches("^[0-9]*$") || Integer.parseInt(size) <= 0) {
            throw new WrongParameterException("Size parameter should be positive number");
        }
        if (params == null || params.isEmpty()) {
            throw new WrongParameterException("At least parameter is invalid");
        }

        List<String> positionFields = List.of("_type", "_id", "key", "name", "fullName", "iata_error_code", "type", "country"
                , "latitude", "longitude", "location_id", "inEurope", "countryCode", "coreCountry", "distance");

        List<String> propertiesToInclude = positionFields.stream()
                .filter(params::contains)
                .collect(Collectors.toList());

        List<FlatPosition> positions = client.invokeJsonGeneratorService(size);

        converter.convert(positions, propertiesToInclude);


    }
}

