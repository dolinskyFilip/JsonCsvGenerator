package io.cardinality.csvconverter.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cardinality.csvconverter.CsvconverterApplication;
import io.cardinality.csvconverter.model.FlatPosition;
import io.cardinality.csvconverter.web.advice.ErrorResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {CsvconverterApplication.class})
@ActiveProfiles({"integrationTest"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PositionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private ClientAndServer mockServer;
    private ObjectMapper mapper;

    @BeforeAll
    void init() {
        mapper = new ObjectMapper();
        mockServer = ClientAndServer.startClientAndServer(8080);
    }

    @AfterAll
    void cleanUp() {
        mockServer.stop();
        while (!mockServer.hasStopped(3, 100l, TimeUnit.MILLISECONDS)) ;
    }

    @BeforeEach
    void resetMockServer() {
        mockServer.reset();
    }

    @Test
    void shouldGenerateJsonAndConvertToCsvCorrect() throws Exception {

        var link = "/generate/csv/2";

        var result = mockMvc.perform(MockMvcRequestBuilders.get(link)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType("text/csv"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();

        Assertions.assertThat(result).isNotNull();

    }

    @Test
    void shouldGenerateExceptionWhenWrongParameter() throws Exception {

        var link = "/generate/csv/stupidParamater12325";

        var result = mockMvc.perform(MockMvcRequestBuilders.get(link)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse();

        var errorResponse = mapper.readValue(result.getContentAsString(), ErrorResponse.class);

        Assertions.assertThat(errorResponse.getMessage()).isEqualTo("Size parameter should be positive number");

    }

    @Test
    void shouldGenerateJsonWithParametersAndConvertToCsvCorrect() throws Exception {

        var link = "/generate/csv/100/filter?property=_id&property=latitude&property=longitude";

        var result = mockMvc.perform(MockMvcRequestBuilders.get(link)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType("text/csv"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();


        Assertions.assertThat(result).isNotNull();
    }

    @Test
    void shouldGenerateExceptionWhenWrongParameterFilter() throws Exception {

        var link = "/generate/csv/100/filter?property=_id23&property=43&property=g";

        var result = mockMvc.perform(MockMvcRequestBuilders.get(link)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse();

        var errorResponse = mapper.readValue(result.getContentAsString(), ErrorResponse.class);

        Assertions.assertThat(errorResponse.getMessage()).isEqualTo("Your parameter(s) doesn't match with Position fields");

    }

}
