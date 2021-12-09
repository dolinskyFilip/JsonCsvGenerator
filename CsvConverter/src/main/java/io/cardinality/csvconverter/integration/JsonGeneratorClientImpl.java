package io.cardinality.csvconverter.integration;

import io.cardinality.csvconverter.integration.model.Position;
import io.cardinality.csvconverter.model.FlatPosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;

@Slf4j
@Component
public class JsonGeneratorClientImpl implements JsonGeneratorClient{

    @Value("${jsongenerator.endpoint.client}")
    private String JSON_GENERATOR_ENDPOINT;

    @Override
    public List<FlatPosition> invokeJsonGeneratorService(String size) {

        log.info("Sending request to JsonApp for generating " +size+" element(s) json list ");

        var client= WebClient.create(JSON_GENERATOR_ENDPOINT+"/"+size);

        var response= client.post()
                .retrieve()
                .bodyToFlux(FlatPosition.class)
                .collectList()
                .block();

        log.info("Received data from JsonApp");

        return response;
    }

}
