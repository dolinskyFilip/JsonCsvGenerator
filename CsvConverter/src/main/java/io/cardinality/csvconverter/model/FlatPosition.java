package io.cardinality.csvconverter.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class FlatPosition {

    private String _type;

    private Long _id;

    private String key;

    private String name;

    private String fullName;

    private Long iata_error_code;

    private String type;

    private String country;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Long location_id;

    private boolean inEurope;

    private String countryCode;

    private boolean coreCountry;

    private Long distance;
}
