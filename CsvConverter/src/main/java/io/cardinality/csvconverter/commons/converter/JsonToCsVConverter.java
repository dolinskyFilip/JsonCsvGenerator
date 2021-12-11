package io.cardinality.csvconverter.commons.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.cardinality.csvconverter.model.FlatPosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class JsonToCsVConverter {

    public byte[] convert(List<FlatPosition> positions, List<String> propertiesToInclude) throws IOException {

        String[] properties = propertiesToInclude.toArray(String[]::new);

        ObjectMapper mapper = new ObjectMapper().addMixIn(FlatPosition.class, DynamicMixIn.class);

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("dynamicFilter", SimpleBeanPropertyFilter.filterOutAllExcept(properties));
        mapper.setFilterProvider(filterProvider);

        JsonNode jsonTree = mapper.convertValue(positions, JsonNode.class);
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
        CsvMapper csvMapper = new CsvMapper();

        log.info("Converting JSON to CSV");

        byte[] result = csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValueAsBytes(jsonTree);

        log.info("CSV created");

        return result;

    }

}
