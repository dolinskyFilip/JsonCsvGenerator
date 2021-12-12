package io.cardinality.csvconverter.service.testdata;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvSchemaFactory {

    public static CsvSchema sample(){
        return CsvSchema.builder()
                .addColumn("_type")
                .addColumn("_id")
                .addColumn("key")
                .addColumn("name")
                .addColumn("fullName")
                .addColumn("iata_error_code")
                .addColumn("type")
                .addColumn("country")
                .addColumn("latitude")
                .addColumn("longitude")
                .addColumn("location_id")
                .addColumn("inEurope")
                .addColumn("countryCode")
                .addColumn("coreCountry")
                .addColumn("distance")
                .build();
    }
}
