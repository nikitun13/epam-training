package by.training.information.dao.parser;

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
class StringToSymbolParserTest {

    private StringToSymbolParser parser;

    @BeforeAll
    void setUp() {
        parser = new StringToSymbolParser();
    }

    @ParameterizedTest
    @MethodSource("dataForParse")
    @Tag("parse")
    void shouldParseInputStringToSymbols(String input, List<Symbol> expected) {
        List<? extends TextComponent> actual = parser.parse(input);
        assertEquals(expected, actual);
    }

    @AfterAll
    void tearDown() {
        parser = null;
    }

    public Stream<Arguments> dataForParse() {
        return Stream.of(
                Arguments.of("hello", List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))),
                Arguments.of("привет", List.of(Symbol.valueOf('п'), Symbol.valueOf('р'), Symbol.valueOf('и'), Symbol.valueOf('в'), Symbol.valueOf('е'), Symbol.valueOf('т'))),
                Arguments.of("h", List.of(Symbol.valueOf('h'))),
                Arguments.of("ё", List.of(Symbol.valueOf('ё'))),
                Arguments.of("Ё", List.of(Symbol.valueOf('Ё'))),
                Arguments.of("4", List.of(Symbol.valueOf('4'))),
                Arguments.of(" ", List.of(Symbol.valueOf(' '))),
                Arguments.of("?", List.of(Symbol.valueOf('?'))),
                Arguments.of(";", List.of(Symbol.valueOf(';'))),
                Arguments.of("-", List.of(Symbol.valueOf('-'))),
                Arguments.of("he", List.of(Symbol.valueOf('h'), Symbol.valueOf('e'))),
                Arguments.of("?!", List.of(Symbol.valueOf('?'), Symbol.valueOf('!'))),
                Arguments.of("...", List.of(Symbol.valueOf('.'), Symbol.valueOf('.'), Symbol.valueOf('.'))),
                Arguments.of("hel-lo", List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('-'), Symbol.valueOf('l'), Symbol.valueOf('o'))),
                Arguments.of("hel7lo", List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('7'), Symbol.valueOf('l'), Symbol.valueOf('o'))),
                Arguments.of("hel7ёж", List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('7'), Symbol.valueOf('ё'), Symbol.valueOf('ж'))),
                Arguments.of("hel-lo its me!",
                        List.of(
                                Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('-'), Symbol.valueOf('l'), Symbol.valueOf('o'), Symbol.valueOf(' '),
                                Symbol.valueOf('i'), Symbol.valueOf('t'), Symbol.valueOf('s'), Symbol.valueOf(' '),
                                Symbol.valueOf('m'), Symbol.valueOf('e'), Symbol.valueOf('!')
                        )
                )
        );
    }
}
