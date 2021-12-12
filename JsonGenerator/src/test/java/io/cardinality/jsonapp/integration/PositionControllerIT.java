package io.cardinality.jsonapp.integration;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cardinality.jsonapp.JsonappApplication;
import io.cardinality.jsonapp.model.FlatPosition;
import io.cardinality.jsonapp.model.Position;
import io.cardinality.jsonapp.web.advice.ErrorResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {JsonappApplication.class})
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
    @DisplayName("test if controller returns 200 for getPositions endpoint")
    void shouldGenerateJsonCorrect() throws Exception {

        var link = "/generate/json/4";

        var result = mockMvc.perform(MockMvcRequestBuilders.post(link)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();

        var positions = mapper.readValue(result.getContentAsString(), new TypeReference<List<Position>>(){});

        Assertions.assertThat(positions).isNotEmpty();
        Assertions.assertThat(positions).hasSize(4);
    }

    @Test
    @DisplayName("test if controller returns 400 when giving bad parameter for getPositions endpoint")
    void shouldGenerateExceptionWhenWrongParameter() throws Exception {

        var link = "/generate/json/stupidParamater12325";

        var result = mockMvc.perform(MockMvcRequestBuilders.post(link)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse();

        var errorResponse = mapper.readValue(result.getContentAsString(), ErrorResponse.class);

        Assertions.assertThat(errorResponse.getMessage()).isEqualTo("Size parameter should be positive number");
    }

    @Test
    @DisplayName("test if controller returns 200 for getFlatPositionsFiltered endpoint")
    void shouldGenerateJsonFlatCorrect() throws Exception {

        var link = "/generate/json/flat/4";

        var result = mockMvc.perform(MockMvcRequestBuilders.post(link)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();

        var flatPositions = mapper.readValue(result.getContentAsString(), new TypeReference<List<FlatPosition>>(){});
        Assertions.assertThat(flatPositions).isNotEmpty();
        Assertions.assertThat(flatPositions).hasSize(4);

    }

    @Test
    @DisplayName("test if controller returns 400 when giving bad parameter for getFlatPositions endpoint")
    void shouldGenerateExceptionForFlatJsonWhenWrongParameter() throws Exception {

        var link = "/generate/json/flat/stupidParamater12325";

        var result = mockMvc.perform(MockMvcRequestBuilders.post(link)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse();

        var errorResponse = mapper.readValue(result.getContentAsString(), ErrorResponse.class);

        Assertions.assertThat(errorResponse.getMessage()).isEqualTo("Size parameter should be positive number");
    }
}