package by.training.information.dao.parser;

import by.training.information.entity.Symbol;
import by.training.information.entity.TextComponent;
import by.training.information.entity.TextComponent.Type;
import by.training.information.entity.TextComposite;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StringToExpressionParserTest {

    private StringToExpressionParser parser;

    @BeforeAll
    void setUp() {
        parser = new StringToExpressionParser();
    }

    @ParameterizedTest
    @MethodSource("dataForParse")
    @Tag("parse")
    void shouldParseInputStringToExpressions(String input, List<TextComponent> expected) {
        List<TextComponent> actual = parser.parse(input);
        assertEquals(expected, actual);
    }

    @AfterAll
    void tearDown() {
        parser = null;
    }

    public Stream<Arguments> dataForParse() {
        return Stream.of(
                Arguments.of("y", List.of((Symbol.valueOf('y')))),
                Arguments.of("30>>>3", List.of(new TextComposite(Type.EXPRESSION, List.of(Symbol.valueOf('3'), Symbol.valueOf('0'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('3'))))),
                Arguments.of("30>>>3, ~6&9|(3&4)", List.of(new TextComposite(Type.EXPRESSION, List.of(Symbol.valueOf('3'), Symbol.valueOf('0'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('3'))), Symbol.valueOf(','), new TextComposite(Type.EXPRESSION, List.of(Symbol.valueOf('~'), Symbol.valueOf('6'), Symbol.valueOf('&'), Symbol.valueOf('9'), Symbol.valueOf('|'), Symbol.valueOf('('), Symbol.valueOf('3'), Symbol.valueOf('&'), Symbol.valueOf('4'), Symbol.valueOf(')'))))),
                Arguments.of("30>>>3, abc ~6&9|(3&4)", List.of(new TextComposite(Type.EXPRESSION, List.of(Symbol.valueOf('3'), Symbol.valueOf('0'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('3'))), Symbol.valueOf(','), Symbol.valueOf('a'), Symbol.valueOf('b'), Symbol.valueOf('c'), new TextComposite(Type.EXPRESSION, List.of(Symbol.valueOf('~'), Symbol.valueOf('6'), Symbol.valueOf('&'), Symbol.valueOf('9'), Symbol.valueOf('|'), Symbol.valueOf('('), Symbol.valueOf('3'), Symbol.valueOf('&'), Symbol.valueOf('4'), Symbol.valueOf(')')))))
        );
    }
}
