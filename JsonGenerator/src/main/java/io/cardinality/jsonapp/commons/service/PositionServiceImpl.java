package io.cardinality.jsonapp.commons.service;

import io.cardinality.jsonapp.commons.exception.WrongParameterException;
import io.cardinality.jsonapp.model.FlatPosition;
import io.cardinality.jsonapp.model.Position;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PositionServiceImpl implements PositionService {

    @Override
    public List<Position> generatePositions(String size) {

        if (size == null || !size.matches("\\d") || Integer.parseInt(size) <= 0) {
            throw new WrongParameterException("Size parameter should be positive number");
        }

        var generator = new EasyRandom(createParameters());

        log.info("Generating " + size + " element(s) JSON list");
        return generator.objects(Position.class, Integer.parseInt(size))
                .collect(Collectors.toList());
    }

    @Override
    public List<FlatPosition> generateFlatPositions(String size) {
        if (size == null || !size.matches("^[0-9]*$") || Integer.parseInt(size) <= 0) {
            throw new WrongParameterException("Size parameter should be positive number");
        }
        var generator = new EasyRandom(createParameters());
        log.info("Generating " + size + " element(s) JSON list");
        return generator.objects(FlatPosition.class, Integer.parseInt(size))
                .collect(Collectors.toList());
    }


    private EasyRandomParameters createParameters() {

        return new EasyRandomParameters()
                .randomize(Long.class, () -> Long.valueOf(new Random().nextInt(9999)))
                .randomize(BigDecimal.class, () -> BigDecimal.valueOf((new Random().nextInt(999999999)), 7))
                .randomize(Boolean.class, new Random()::nextBoolean)
                .randomize(String.class, this::getRandomString)
                .stringLengthRange(3, 10)
                .scanClasspathForConcreteTypes(true);
    }

    protected String getRandomString() {
        String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        while (result.length() < 10) { // length of the random string.
            int index = (int)(random.nextFloat() * chars.length());
            result.append(chars.charAt(index));
        }
        return result.toString();

    }
}
