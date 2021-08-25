package by.training.classes.service.impl;

import by.training.classes.entity.Sentence;
import by.training.classes.entity.Text;
import by.training.classes.entity.Word;
import by.training.classes.service.ServiceException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextEditorServiceImplTest {

    private static TextEditorServiceImpl service;

    private Text sourceText;

    @BeforeAll
    static void setUp() {
        service = new TextEditorServiceImpl();
    }

    @BeforeEach
    void setUpText() {
        sourceText = new Text(
                new Sentence(List.of(new Word("It's"), new Word("header."))),
                List.of(
                        new Sentence(List.of(new Word("First"), new Word("sentence"))),
                        new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                        new Sentence(List.of(new Word("\nThird"), new Word("sentence"))
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("positiveDataForAddSentence")
    @Tag("addSentence")
    void mustAddSentenceIfIndexIsValid(int index, Text expected) throws ServiceException {
        Sentence newSentence = new Sentence(List.of(new Word("It's"), new Word("new"), new Word("sentence")));
        service.addSentence(sourceText, index, newSentence);
        assertEquals(expected, sourceText,
                () -> "must add new sentence to " + index + " position"
        );
    }

    @ParameterizedTest
    @MethodSource("invalidDataForAddSentence")
    @Tag("addSentence")
    void mustThrowServiceExceptionIfInvalidDataReceivedForAddSentence(Text text, int index, Sentence sentence) {
        assertThrows(ServiceException.class,
                () -> service.addSentence(text, index, sentence),
                () -> "must throw " + ServiceException.class.getName() + " for invalid input"
        );
    }

    @ParameterizedTest
    @MethodSource("indexesToRemoveSentence")
    @Tag("removeSentence")
    void mustRemoveSentenceIfIndexIsCorrect(int index, Text expected) throws ServiceException {
        service.removeSentence(sourceText, index);
        assertEquals(expected, sourceText, () -> "must remove " + index + " sentence");
    }

    @ParameterizedTest
    @MethodSource("invalidDataForRemoveSentence")
    @Tag("removeSentence")
    void mustThrowServiceExceptionIfReceivedInvalidDataForRemoveSentence(Text text, int index) {
        assertThrows(ServiceException.class,
                () -> service.removeSentence(text, index),
                () -> "must throw " + ServiceException.class.getName() + " for invalid input"
        );
    }

    @Test
    @Tag("changeHeader")
    void mustChangeHeaderIfValidDataReceived() throws ServiceException {
        Sentence newHeader = new Sentence(List.of(new Word("New"), new Word("header")));
        Text expected = new Text(
                new Sentence(List.of(new Word("New"), new Word("header"))),
                List.of(
                        new Sentence(List.of(new Word("First"), new Word("sentence"))),
                        new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                        new Sentence(List.of(new Word("\nThird"), new Word("sentence"))
                        )
                )
        );
        service.changeHeader(sourceText, newHeader);

        assertEquals(expected, sourceText, "must change header");
    }

    @ParameterizedTest
    @MethodSource("invalidDataForChangeHeader")
    @Tag("changeHeader")
    void mustThrowServiceExceptionIfReceivedInvalidDataForChangeHeader(Text text, Sentence header) {
        assertThrows(ServiceException.class,
                () -> service.changeHeader(text, header),
                () -> "must throw " + ServiceException.class.getName() + " for invalid input"
        );
    }

    @AfterEach
    void tearDownText() {
        sourceText = null;
    }

    @AfterAll
    static void tearDown() {
        service = null;
    }

    public static Stream<Arguments> positiveDataForAddSentence() {
        return Stream.of(
                Arguments.of(0, new Text(
                                new Sentence(List.of(new Word("It's"), new Word("header."))),
                                List.of(
                                        new Sentence(List.of(new Word("It's"), new Word("new"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("\nThird"), new Word("sentence"))
                                        )
                                )
                        )
                ),
                Arguments.of(1, new Text(
                                new Sentence(List.of(new Word("It's"), new Word("header."))),
                                List.of(
                                        new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("It's"), new Word("new"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("\nThird"), new Word("sentence"))
                                        )
                                )
                        )
                ),
                Arguments.of(2, new Text(
                                new Sentence(List.of(new Word("It's"), new Word("header."))),
                                List.of(
                                        new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("It's"), new Word("new"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("\nThird"), new Word("sentence"))
                                        )
                                )
                        )
                ),
                Arguments.of(3, new Text(
                                new Sentence(List.of(new Word("It's"), new Word("header."))),
                                List.of(
                                        new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("\nThird"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("It's"), new Word("new"), new Word("sentence")))
                                )
                        )
                )
        );
    }

    public static Stream<Arguments> invalidDataForAddSentence() {
        return Stream.of(
                Arguments.of(null, 1, new Sentence(List.of(new Word("Second"), new Word("sentence")))),
                Arguments.of(new Text(
                        new Sentence(List.of(new Word("It's"), new Word("header."))),
                        List.of(
                                new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                                new Sentence(List.of(new Word("\nThird"), new Word("sentence"))),
                                new Sentence(List.of(new Word("It's"), new Word("new"), new Word("sentence")))
                        )
                ), -1, new Sentence(List.of(new Word("Second"), new Word("sentence")))),
                Arguments.of(new Text(
                        new Sentence(List.of(new Word("It's"), new Word("header."))),
                        List.of(
                                new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                                new Sentence(List.of(new Word("\nThird"), new Word("sentence"))),
                                new Sentence(List.of(new Word("It's"), new Word("new"), new Word("sentence")))
                        )
                ), 5, new Sentence(List.of(new Word("Second"), new Word("sentence")))),
                Arguments.of(new Text(
                        new Sentence(List.of(new Word("It's"), new Word("header."))),
                        List.of(
                                new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                                new Sentence(List.of(new Word("\nThird"), new Word("sentence"))),
                                new Sentence(List.of(new Word("It's"), new Word("new"), new Word("sentence")))
                        )
                ), 3, null),
                Arguments.of(null, 3, null),
                Arguments.of(null, -3, null)
        );
    }

    public static Stream<Arguments> indexesToRemoveSentence() {
        return Stream.of(
                Arguments.of(0, new Text(
                                new Sentence(List.of(new Word("It's"), new Word("header."))),
                                List.of(
                                        new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("\nThird"), new Word("sentence"))
                                        )
                                )
                        )
                ),
                Arguments.of(1, new Text(
                                new Sentence(List.of(new Word("It's"), new Word("header."))),
                                List.of(
                                        new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("\nThird"), new Word("sentence"))
                                        )
                                )
                        )
                ),
                Arguments.of(2, new Text(
                                new Sentence(List.of(new Word("It's"), new Word("header."))),
                                List.of(
                                        new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("Second"), new Word("sentence")))
                                )
                        )
                )
        );
    }

    public static Stream<Arguments> invalidDataForRemoveSentence() {
        return Stream.of(
                Arguments.of(null, 1),
                Arguments.of(new Text(
                        new Sentence(List.of(new Word("It's"), new Word("header."))),
                        List.of(
                                new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                                new Sentence(List.of(new Word("\nThird"), new Word("sentence"))),
                                new Sentence(List.of(new Word("It's"), new Word("new"), new Word("sentence")))
                        )
                ), -1),
                Arguments.of(new Text(
                        new Sentence(List.of(new Word("It's"), new Word("header."))),
                        List.of(
                                new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                                new Sentence(List.of(new Word("\nThird"), new Word("sentence"))),
                                new Sentence(List.of(new Word("It's"), new Word("new"), new Word("sentence")))
                        )
                ), 4),
                Arguments.of(new Text(
                        new Sentence(List.of(new Word("It's"), new Word("header."))),
                        List.of(
                                new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                                new Sentence(List.of(new Word("\nThird"), new Word("sentence"))),
                                new Sentence(List.of(new Word("It's"), new Word("new"), new Word("sentence")))
                        )
                ), 7),
                Arguments.of(null, 5),
                Arguments.of(null, -3)
        );
    }

    public static Stream<Arguments> invalidDataForChangeHeader() {
        return Stream.of(
                Arguments.of(null, new Sentence(List.of(new Word("First"), new Word("sentence")))),
                Arguments.of(new Text(
                        new Sentence(List.of(new Word("It's"), new Word("header."))),
                        List.of(
                                new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                                new Sentence(List.of(new Word("\nThird"), new Word("sentence"))),
                                new Sentence(List.of(new Word("It's"), new Word("new"), new Word("sentence")))
                        )
                ), null),
                Arguments.of(null, null)
        );
    }
}
