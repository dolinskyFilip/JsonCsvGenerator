package io.cardinality.jsonapp.model;

import lombok.Data;
@Data
public class Position  {

    private String _type;

    private Long _id;

    private String key;

    private String name;

    private String fullName;

    private Long iata_error_code;

    private String type;

    private String country;

    private GeoPosition geo_position;

    private Long location_id;

    private boolean inEurope;

    private String countryCode;

    private boolean coreCountry;

    private Long distance;


}
