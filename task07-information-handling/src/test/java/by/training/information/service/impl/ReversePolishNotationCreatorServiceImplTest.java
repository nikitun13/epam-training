package by.training.information.service.impl;

import by.training.information.entity.ReversePolishNotation;
import by.training.information.service.ServiceException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReversePolishNotationCreatorServiceImplTest {

    private ReversePolishNotationCreatorServiceImpl service;

    @BeforeAll
    void setUp() {
        service = new ReversePolishNotationCreatorServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("validDataForCreateNotation")
    @Tag("createNotation")
    void shoutCreateNotationIfValidDataReceived(String expression,
                                                ReversePolishNotation expected)
            throws ServiceException {
        ReversePolishNotation actual = service.createNotation(expression);
        assertEquals(expected, actual,
                () -> "result for expression %s must be %s".formatted(expected, expected)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("invalidDataForCreateNotation")
    @Tag("createNotation")
    void shouldThrowServiceExceptionIfInvalidDataReceived(String expression) {
        assertThrows(ServiceException.class,
                () -> service.createNotation(expression),
                () -> "must throw " + ServiceException.class + " for invalid input: " + expression
        );
    }

    @AfterAll
    void tearDown() {
        service = null;
    }

    public Stream<Arguments> validDataForCreateNotation() {
        return Stream.of(
                Arguments.of("15>>(8<<9)&15<<12|(15>>>4)",
                        new ReversePolishNotation(new ArrayDeque<>(List.of("15", "8", "9", "<<", ">>", "15", "12", "<<", "&", "15", "4", ">>>", "|")))),
                Arguments.of("13<<2",
                        new ReversePolishNotation(new ArrayDeque<>(List.of("13", "2", "<<")))),
                Arguments.of("30>>>3",
                        new ReversePolishNotation(new ArrayDeque<>(List.of("30", "3", ">>>")))),
                Arguments.of("~6&9|(3&4)",
                        new ReversePolishNotation(new ArrayDeque<>(List.of("6", "~", "9", "&", "3", "4", "&", "|")))),
                Arguments.of("5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)",
                        new ReversePolishNotation(new ArrayDeque<>(List.of("5", "1", "2", "&", "3", "4", "25", "5", "^", "6", "47", "&", "|", "&", "3", "|", "|", "2", "|", "&", "1", "|", "|")))),
                Arguments.of("(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78",
                        new ReversePolishNotation(new ArrayDeque<>(List.of("71", "~", "2", "3", "&", "3", "2", "1", "2", ">>", "&", "2", "|", "2", "&", "|", "|", "10", "2", "&", "|", "&", "78", "|")))),
                Arguments.of("(8^5|1&2<<(2|5>>2&71))|1200",
                        new ReversePolishNotation(new ArrayDeque<>(List.of("8", "5", "^", "1", "2", "2", "5", "2", ">>", "71", "&", "|", "<<", "&", "|", "1200", "|"))))
        );
    }

    public Stream<Arguments> invalidDataForCreateNotation() {
        return Stream.of(
                Arguments.of("15>>(8<<9)&15<<1f2|(15>>>4)"),
                Arguments.of("adsa"),
                Arguments.of("Hello"),
                Arguments.of("expression"),
                Arguments.of(" 15>>(8<<9)&15<<12|(15>>>4)"),
                Arguments.of("15>>(8<<9)&15<<12|(15>>>4) "),
                Arguments.of("124"),
                Arguments.of("124>>"),
                Arguments.of("x")
        );
    }
}
