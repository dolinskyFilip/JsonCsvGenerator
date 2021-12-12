package io.cardinality.csvconverter.service;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.cardinality.csvconverter.commons.converter.JsonToCsVConverter;
import io.cardinality.csvconverter.commons.exception.WrongParameterException;
import io.cardinality.csvconverter.integration.JsonGeneratorClient;
import io.cardinality.csvconverter.service.testdata.CsvSchemaFactory;
import io.cardinality.csvconverter.service.testdata.PositionListFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class PositionServiceImplTest {

    @InjectMocks
    PositionServiceImpl positionService;

    @Mock
    JsonToCsVConverter converter;

    @Mock
    JsonGeneratorClient client;


    @Test
    @DisplayName("Test getPositions method returns exception for null value")
    void shouldGetPositionsReturnExceptionWhenParamaterNull() {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            positionService.getPositions(null);
        }).isInstanceOf(WrongParameterException.class).hasMessage("Size parameter should be positive number");
    }

    @Test
    @DisplayName("Test if getFlatPositions method returns exception for empty list ")
    void shouldGetFilteredPositionsReturnExceptionWhenEmptyListGiven() {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            positionService.getFilteredPositions("4", Collections.emptyList());
        }).isInstanceOf(WrongParameterException.class).hasMessage("At least parameter is invalid");
    }

    @Test
    @DisplayName("Test if getFlatPositions method returns exception for parameter, that doesn't match with Position field")
    void shouldGetFilteredPositionsReturnExceptionWhenListParameterNotMatched() {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            positionService.getFilteredPositions("4", List.of("notmatch", "stupidParameter"));
        }).isInstanceOf(WrongParameterException.class).hasMessage("Your parameter(s) doesn't match with Position fields");
    }

    @Test
    @DisplayName("Test if getPositions method returns CSV correct")
    void shouldGetPositionsReturnCsvCorrect() throws IOException {

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchemaFactory.sample();
        List<String> propertiesToInclude = List.of("_type", "_id", "name", "type", "latitude", "longitude");
        var testPositions = PositionListFactory.sample();
        byte[] jsonByte = csvMapper.writer(schema).writeValueAsBytes(testPositions);

        Mockito.when(client.invokeJsonGeneratorService(Mockito.anyString())).thenReturn(testPositions);
        Mockito.when(converter.convert(Mockito.anyList(), Mockito.anyList())).thenReturn(jsonByte);

        var positions = positionService.getPositions("4");

        Mockito.verify(client).invokeJsonGeneratorService("4");
        Mockito.verify(converter).convert(testPositions,propertiesToInclude);

        Assertions.assertEquals(jsonByte, positions);
        Assertions.assertNotNull(positions);
    }

    @Test
    @DisplayName("Test if getFilteredPositions returns CSV correct")
    void shouldGetFilteredPositionsReturnCsvCorrect() throws IOException {

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchemaFactory.sample();
        List<String> propertiesToInclude = List.of("_type", "_id");
        var testPositions = PositionListFactory.sample();
        byte[] jsonByte = csvMapper.writer(schema).writeValueAsBytes(testPositions);

        Mockito.when(client.invokeJsonGeneratorService(Mockito.anyString())).thenReturn(testPositions);
        Mockito.when(converter.convert(Mockito.anyList(), Mockito.anyList())).thenReturn(jsonByte);

        var positions = positionService.getFilteredPositions("4",propertiesToInclude);

        Mockito.verify(client).invokeJsonGeneratorService("4");
        Mockito.verify(converter).convert(testPositions,propertiesToInclude);

        Assertions.assertEquals(jsonByte, positions);
        Assertions.assertNotNull(positions);
    }
}