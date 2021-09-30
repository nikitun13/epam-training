package by.training.information.service.impl;

import by.training.information.entity.Symbol;
import by.training.information.entity.TextComponent;
import by.training.information.service.ServiceException;
import by.training.information.service.ServiceFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TextSortingServiceImplTest {

    private static TextComponent inputText;

    private TextSortingServiceImpl service;

    @BeforeAll
    void setUp() throws ServiceException {
        service = new TextSortingServiceImpl();
        inputText = ServiceFactory.getInstance()
                .getTextService()
                .readTextFromFile("src/test/resources/text/text.txt");
    }

    @Test
    @Tag("sortParagraphsByNumberOfSentences")
    void shouldSortParagraphsByNumberOfSentences() throws ServiceException {
        TextComponent expected = inputText.copyComponent();
        List<TextComponent> paragraphs = expected.getChildrenComponents();
        paragraphs.add(0, paragraphs.remove(3));
        paragraphs.add(0, paragraphs.remove(3));

        TextComponent actual = service.sortParagraphsByNumberOfSentences(inputText);

        assertEquals(expected, actual);
    }

    @Test
    @Tag("sortParagraphsByNumberOfSentences")
    void shouldThrowExceptionIfInvalidDataReceivedForSortParagraphs() {
        assertThrows(ServiceException.class,
                () -> service.sortParagraphsByNumberOfSentences(null)
        );
    }

    @Test
    @Tag("sortWordsInSentenceByLength")
    void sortWordsInSentenceByLength() throws ServiceException {
        TextComponent inputText = ServiceFactory.getInstance()
                .getTextService()
                .readTextFromFile("src/test/resources/text/textForSortByWordLength.txt");
        TextComponent expected = inputText.copyComponent();
        List<TextComponent> paragraphs = expected.getChildrenComponents();
        List<TextComponent> sentences = paragraphs.get(0).getChildrenComponents();
        List<TextComponent> secondSentenceComponents = sentences.get(1).getChildrenComponents();
        secondSentenceComponents.add(0, secondSentenceComponents.remove(1));
        List<TextComponent> thirdSentenceComponents = sentences.get(2).getChildrenComponents();
        thirdSentenceComponents.add(0, thirdSentenceComponents.remove(2));
        thirdSentenceComponents.add(0, thirdSentenceComponents.remove(2));
        List<TextComponent> fourthSentenceComponents = sentences.get(3).getChildrenComponents();
        fourthSentenceComponents.add(0, fourthSentenceComponents.remove(1));

        TextComponent actual = service.sortWordsInSentenceByLength(inputText);

        assertEquals(expected, actual);
    }

    @Test
    @Tag("sortWordsInSentenceByLength")
    void shouldThrowExceptionIfInvalidDataReceivedForSortWords() {
        assertThrows(ServiceException.class,
                () -> service.sortWordsInSentenceByLength(null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidDataForSortLexemes")
    @Tag("sortLexemesInTextByNumberOfGivenSymbolDescThenByAlphabet")
    void shouldThrowExceptionIfInvalidDataReceivedForSortLexemes(TextComponent component, Symbol symbol) {
        assertThrows(ServiceException.class,
                () -> service.sortLexemesInTextByNumberOfGivenSymbolDescThenByAlphabet(component, symbol)
        );
    }

    @AfterAll
    void tearDown() {
        service = null;
    }

    public Stream<Arguments> invalidDataForSortLexemes() {
        return Stream.of(
                Arguments.of(null, Symbol.valueOf('a')),
                Arguments.of(inputText, null)
        );
    }
}
