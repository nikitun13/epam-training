package by.training.information.dao.parser;

import by.training.information.entity.Lexeme;
import by.training.information.entity.Symbol;
import by.training.information.entity.TextComponent;
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
class StringToLexemesParserTest {

    private StringToLexemesParser parser;

    @BeforeAll
    void setUp() {
        parser = new StringToLexemesParser();
    }

    @ParameterizedTest
    @MethodSource("dataForParse")
    @Tag("parse")
    void shouldParseInputStringToLexemes(String input, List<TextComponent> expected) {
        List<TextComponent> actual = parser.parse(input);
        assertEquals(expected, actual);
    }

    @AfterAll
    void tearDown() {
        parser = null;
    }

    public Stream<Arguments> dataForParse() {
        return Stream.of(
                Arguments.of("30>>>3", List.of(new Lexeme(List.of(Symbol.valueOf('3'), Symbol.valueOf('0'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('3'))))),
                Arguments.of("30>>>3, ~6&9|(3&4)", List.of(new Lexeme(List.of(Symbol.valueOf('3'), Symbol.valueOf('0'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('3'), Symbol.valueOf(','))), new Lexeme(List.of(Symbol.valueOf('~'), Symbol.valueOf('6'), Symbol.valueOf('&'), Symbol.valueOf('9'), Symbol.valueOf('|'), Symbol.valueOf('('), Symbol.valueOf('3'), Symbol.valueOf('&'), Symbol.valueOf('4'), Symbol.valueOf(')'))))),
                Arguments.of("30>>>3, abc ~6&9|(3&4)", List.of(new Lexeme(List.of(Symbol.valueOf('3'), Symbol.valueOf('0'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('3'), Symbol.valueOf(','))), new Lexeme(List.of(Symbol.valueOf('a'), Symbol.valueOf('b'), Symbol.valueOf('c'))), new Lexeme(List.of(Symbol.valueOf('~'), Symbol.valueOf('6'), Symbol.valueOf('&'), Symbol.valueOf('9'), Symbol.valueOf('|'), Symbol.valueOf('('), Symbol.valueOf('3'), Symbol.valueOf('&'), Symbol.valueOf('4'), Symbol.valueOf(')'))))),
                Arguments.of("y", List.of(new Lexeme(List.of(Symbol.valueOf('y'))))),
                Arguments.of("Y", List.of(new Lexeme(List.of(Symbol.valueOf('Y'))))),
                Arguments.of("I!", List.of(new Lexeme(List.of(Symbol.valueOf('I'), Symbol.valueOf('!'))))),
                Arguments.of("hello", List.of(new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))))),
                Arguments.of("hello...", List.of(new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'), Symbol.valueOf('.'), Symbol.valueOf('.'), Symbol.valueOf('.'))))),
                Arguments.of("hello hello", List.of(new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))))),
                Arguments.of("hello, hello", List.of(new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'), Symbol.valueOf(','))), new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))))),
                Arguments.of("hello-hello", List.of(new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'), Symbol.valueOf('-'), Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))))),
                Arguments.of("hello, hello!", List.of(new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'), Symbol.valueOf(','))), new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'), Symbol.valueOf('!'))))),
                Arguments.of("hello hello?", List.of(new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'), Symbol.valueOf('?'))))),
                Arguments.of("hello Привет", List.of(new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Lexeme(List.of(Symbol.valueOf('П'), Symbol.valueOf('р'), Symbol.valueOf('и'), Symbol.valueOf('в'), Symbol.valueOf('е'), Symbol.valueOf('т'))))),
                Arguments.of("hello-Привет!", List.of(new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'), Symbol.valueOf('-'), Symbol.valueOf('П'), Symbol.valueOf('р'), Symbol.valueOf('и'), Symbol.valueOf('в'), Symbol.valueOf('е'), Symbol.valueOf('т'), Symbol.valueOf('!'))))),
                Arguments.of("hello Привет?!", List.of(new Lexeme(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Lexeme(List.of(Symbol.valueOf('П'), Symbol.valueOf('р'), Symbol.valueOf('и'), Symbol.valueOf('в'), Symbol.valueOf('е'), Symbol.valueOf('т'), Symbol.valueOf('?'), Symbol.valueOf('!')))))
        );
    }
}
