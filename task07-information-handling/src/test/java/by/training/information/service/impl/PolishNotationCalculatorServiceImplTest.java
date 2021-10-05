package by.training.information.service.impl;

import by.training.information.entity.ReversePolishNotation;
import by.training.information.service.ServiceException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PolishNotationCalculatorServiceImplTest {

    private PolishNotationCalculatorServiceImpl service;

    @BeforeAll
    void setUp() {
        service = new PolishNotationCalculatorServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("validDataForCalculateExpression")
    @Tag("calculateExpression")
    void calculateExpression(ReversePolishNotation notation, int expected) throws ServiceException {
        int actual = service.calculateExpression(notation);

        assertEquals(expected, actual,
                () -> "should be %d for notation: %s".formatted(expected, notation)
        );
    }

    @Test
    @Tag("calculateExpression")
    void shouldThrowServiceExceptionIfInvalidDataReceived() {
        assertThrows(ServiceException.class,
                () -> service.calculateExpression(null)
        );
    }

    @AfterAll
    void tearDown() {
        service = null;
    }

    public Stream<Arguments> validDataForCalculateExpression() throws ServiceException {
        ReversePolishNotationCreatorServiceImpl creatorService = new ReversePolishNotationCreatorServiceImpl();
        return Stream.of(
                Arguments.of(creatorService.createNotation("~6&9&(3|4)"), 1),
                Arguments.of(creatorService.createNotation("13<<2"), 52),
                Arguments.of(creatorService.createNotation("~6&9|(3&4)"), 9),
                Arguments.of(creatorService.createNotation("5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)"), 5),
                Arguments.of(creatorService.createNotation("(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78"), 78),
                Arguments.of(creatorService.createNotation("(8^5|1&2<<(2|5>>2&71))|1200"), 1213),
                Arguments.of(creatorService.createNotation("30>>>3"), 3)
        );
    }
}
