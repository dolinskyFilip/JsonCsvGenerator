package io.cardinality.csvconverter.integration.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GeoPosition {

    private BigDecimal latitude;

    private BigDecimal longitude;
}
