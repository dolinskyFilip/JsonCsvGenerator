package io.cardinality.jsonapp.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GeoPosition {

    private BigDecimal latitude;

    private BigDecimal longitude;
}
