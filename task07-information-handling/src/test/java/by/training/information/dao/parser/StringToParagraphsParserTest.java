package by.training.information.dao.parser;

import by.training.information.entity.Symbol;
import by.training.information.entity.TextComponent;
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

import static by.training.information.entity.TextComposite.Type;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StringToParagraphsParserTest {

    private StringToParagraphsParser parser;

    @BeforeAll
    void setUp() {
        parser = new StringToParagraphsParser();
    }

    @ParameterizedTest
    @MethodSource("dataForParse")
    @Tag("parse")
    void shouldParseInputStringToParagraphs(String input, List<TextComponent> expected) {
        List<TextComponent> actual = parser.parse(input);
        assertEquals(expected, actual);
    }

    @AfterAll
    void tearDown() {
        parser = null;
    }

    public Stream<Arguments> dataForParse() {
        return Stream.of(
                Arguments.of("It 30>>>3.", List.of(new TextComposite(Type.PARAGRAPH, List.of(Symbol.valueOf('I'), Symbol.valueOf('t'), Symbol.valueOf(' '), Symbol.valueOf('3'), Symbol.valueOf('0'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('3'), Symbol.valueOf('.'))))),
                Arguments.of("Hello...", List.of(new TextComposite(Type.PARAGRAPH, List.of(Symbol.valueOf('H'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'), Symbol.valueOf('.'), Symbol.valueOf('.'), Symbol.valueOf('.'))))),
                Arguments.of("It 30>>>3.\nHello...", List.of(new TextComposite(Type.PARAGRAPH, List.of(Symbol.valueOf('I'), Symbol.valueOf('t'), Symbol.valueOf(' '), Symbol.valueOf('3'), Symbol.valueOf('0'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('3'), Symbol.valueOf('.'))), new TextComposite(Type.PARAGRAPH, List.of(Symbol.valueOf('H'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'), Symbol.valueOf('.'), Symbol.valueOf('.'), Symbol.valueOf('.'))))),
                Arguments.of("Hello...\nIt 30>>>3.", List.of(new TextComposite(Type.PARAGRAPH, List.of(Symbol.valueOf('H'), Symbol.valueOf('e'), Symbol.valueOf('l'), Symbol.valueOf('l'), Symbol.valueOf('o'), Symbol.valueOf('.'), Symbol.valueOf('.'), Symbol.valueOf('.'))), new TextComposite(Type.PARAGRAPH, List.of(Symbol.valueOf('I'), Symbol.valueOf('t'), Symbol.valueOf(' '), Symbol.valueOf('3'), Symbol.valueOf('0'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('>'), Symbol.valueOf('3'), Symbol.valueOf('.')))))
        );
    }
}
