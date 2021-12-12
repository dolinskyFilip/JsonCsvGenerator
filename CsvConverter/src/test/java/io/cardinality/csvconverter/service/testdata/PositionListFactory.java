package io.cardinality.csvconverter.service.testdata;

import io.cardinality.csvconverter.model.FlatPosition;

import java.math.BigDecimal;
import java.util.List;

public class PositionListFactory {

    public static List<FlatPosition> sample(){
        var position =new FlatPosition();
        position.set_id(6347L);
        position.set_type("Position");
        position.setName("Oksywska");
        position.setLatitude(new BigDecimal(51.0855422));
        position.setLongitude(new BigDecimal(16.9987442));

        var position2 = new FlatPosition();
        position2.set_id(6347L);
        position2.set_type("Position");
        position2.setName("Oksywska");
        position2.setLatitude(new BigDecimal(51.0855422));
        position2.setLongitude(new BigDecimal(16.9987442));

       return List.of(position, position2);

    }
}
