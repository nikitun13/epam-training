package by.training.information.dao.parser;

import by.training.information.entity.Symbol;
import by.training.information.entity.TextComponent;
import by.training.information.entity.Word;
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
class WordParserTest {

    private WordParser parser;

    @BeforeAll
    void setUp() {
        parser = new WordParser();
    }

    @ParameterizedTest
    @MethodSource("dataForParse")
    @Tag("parse")
    void shouldParseInputStringToWords(String input, List<Word> expected) {
        List<? extends TextComponent> actual = parser.parse(input);
        assertEquals(expected, actual);
    }

    @AfterAll
    void tearDown() {
        parser = null;
    }

    public Stream<Arguments> dataForParse() {
        return Stream.of(
                Arguments.of("y", List.of(new Word(List.of(Symbol.valueOf('y'))))),
                Arguments.of("Y", List.of(new Word(List.of(Symbol.valueOf('Y'))))),
                Arguments.of("hello", List.of(new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))))),
                Arguments.of("hello...", List.of(new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))))),
                Arguments.of("hello hello", List.of(new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))))),
                Arguments.of("hello, hello", List.of(new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))))),
                Arguments.of("hello-hello", List.of(new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))))),
                Arguments.of("hello, hello!", List.of(new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))))),
                Arguments.of("hello hello?", List.of(new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))))),
                Arguments.of("hello Привет", List.of(new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Word(List.of(Symbol.valueOf('П'), Symbol.valueOf('р'), Symbol.valueOf('и'), Symbol.valueOf('в'), Symbol.valueOf('е'), Symbol.valueOf('т'))))),
                Arguments.of("hello-Привет!", List.of(new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Word(List.of(Symbol.valueOf('П'), Symbol.valueOf('р'), Symbol.valueOf('и'), Symbol.valueOf('в'), Symbol.valueOf('е'), Symbol.valueOf('т'))))),
                Arguments.of("434235? hello-Привет!", List.of(new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Word(List.of(Symbol.valueOf('П'), Symbol.valueOf('р'), Symbol.valueOf('и'), Symbol.valueOf('в'), Symbol.valueOf('е'), Symbol.valueOf('т'))))),
                Arguments.of("hello Привет?!", List.of(new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Word(List.of(Symbol.valueOf('П'), Symbol.valueOf('р'), Symbol.valueOf('и'), Symbol.valueOf('в'), Symbol.valueOf('е'), Symbol.valueOf('т'))))),
                Arguments.of("hello Привет?! Ёж", List.of(new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Word(List.of(Symbol.valueOf('П'), Symbol.valueOf('р'), Symbol.valueOf('и'), Symbol.valueOf('в'), Symbol.valueOf('е'), Symbol.valueOf('т'))), new Word(List.of(Symbol.valueOf('Ё'), Symbol.valueOf('ж'))))),
                Arguments.of("hello Привет?! ёж", List.of(new Word(List.of(Symbol.valueOf('h'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'))), new Word(List.of(Symbol.valueOf('П'), Symbol.valueOf('р'), Symbol.valueOf('и'), Symbol.valueOf('в'), Symbol.valueOf('е'), Symbol.valueOf('т'))), new Word(List.of(Symbol.valueOf('ё'), Symbol.valueOf('ж')))))
        );
    }
}
