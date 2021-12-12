package io.cardinality.jsonapp.commons.service;

import io.cardinality.jsonapp.commons.exception.WrongParameterException;
import io.cardinality.jsonapp.model.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PositionServiceImplTest {

    private final PositionServiceImpl positionService = new PositionServiceImpl();

    @Test
    @DisplayName("Test if service returns Position correct")
    void shouldGeneratePositionsCorrect() {
        var result = positionService.generatePositions("4");

        Assertions.assertThat(result).isNotNull()
                .isNotEmpty()
                .hasSize(4);
        Assertions.assertThat(result.get(0).getGeo_position().getLatitude()).isNotNull();
        Assertions.assertThat(result.get(0).getGeo_position().getLatitude()).isPositive();
        Assertions.assertThat(result.get(0).get_id()).isBetween(0L, 9999L);
    }

    @Test
    @DisplayName("Test if service returns Flat Position correct")
    void shouldGenerateFlatPositionsCorrect() {
        var result = positionService.generateFlatPositions("4");

        Assertions.assertThat(result).isNotNull()
                .isNotEmpty()
                .hasSize(4);
        Assertions.assertThat(result.get(0).getLatitude()).isNotNull();
        Assertions.assertThat(result.get(0).getLatitude()).isPositive();
        Assertions.assertThat(result.get(0).get_id()).isBetween(0L, 9999L);
    }

    @Test
    @DisplayName("Test if service returns exception for paramater,that doesn't match with Position field ")
    void shouldReturnExceptionWhenParamaterNotMatchedinGeneratePositions() {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            positionService.generatePositions("adfasd34325");
        }).isInstanceOf(WrongParameterException.class).hasMessage("Size parameter should be positive number");

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            positionService.generateFlatPositions("adfasd34325");
        }).isInstanceOf(WrongParameterException.class).hasMessage("Size parameter should be positive number");
    }


    @Test
    @DisplayName("Test if service returns exception for null parameter")
    void shouldReturnExceptionWhenNullGiven() {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            positionService.generatePositions(null);
        }).isInstanceOf(WrongParameterException.class).hasMessage("Size parameter should be positive number");
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            positionService.generateFlatPositions(null);
        }).isInstanceOf(WrongParameterException.class).hasMessage("Size parameter should be positive number");
    }


    @Test
    @DisplayName("Test if service returns exception for negative parameter")
    void shouldReturnExceptionWhenNegativeParameter() {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            positionService.generatePositions("-1233434");
        }).isInstanceOf(WrongParameterException.class).hasMessage("Size parameter should be positive number");
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            positionService.generatePositions("-1233434");
        }).isInstanceOf(WrongParameterException.class).hasMessage("Size parameter should be positive number");
    }


}