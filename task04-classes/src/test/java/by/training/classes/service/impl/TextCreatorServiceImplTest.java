package by.training.classes.service.impl;

import by.training.classes.entity.Sentence;
import by.training.classes.entity.Text;
import by.training.classes.entity.Word;
import by.training.classes.service.ServiceException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TextCreatorServiceImplTest {

    private TextCreatorServiceImpl service;

    @BeforeAll
    void setUp() {
        service = new TextCreatorServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("testDataForCreateText")
    void createSentenceIfValidDataReceived(String value, Text expected) throws ServiceException {
        Text actual = service.createText(value);
        assertEquals(expected, actual,
                () -> "must create Sentence: " + expected + " for string: " + value
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("negativeDataForCreateText")
    @Tag("createSentence")
    void throwServiceExceptionIfInputIsInvalid(String value) {
        assertThrows(ServiceException.class,
                () -> service.createText(value),
                () -> "must throw " + ServiceException.class.getName() + " for blank or null data"
        );
    }

    @AfterAll
    void tearDown() {
        service = null;
    }

    public Stream<Arguments> testDataForCreateText() {
        return Stream.of(
                Arguments.of("It's header\n\nFirst sentence. Second sentence.", new Text(new Sentence(List.of(new Word("It's"), new Word("header"))),
                                List.of(
                                        new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("Second"), new Word("sentence")))
                                )
                        )
                ),
                Arguments.of("It's header.\n\nFirst sentence. Second sentence.", new Text(new Sentence(List.of(new Word("It's"), new Word("header."))),
                                List.of(
                                        new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("Second"), new Word("sentence")))
                                )
                        )
                ),
                Arguments.of("It's header.\n\nFirst sentence.\nSecond sentence.", new Text(new Sentence(List.of(new Word("It's"), new Word("header."))),
                                List.of(
                                        new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("\nSecond"), new Word("sentence")))
                                )
                        )
                ),
                Arguments.of("It's header\n\nFirst sentence.\nSecond sentence.", new Text(new Sentence(List.of(new Word("It's"), new Word("header"))),
                                List.of(
                                        new Sentence(List.of(new Word("First"), new Word("sentence"))),
                                        new Sentence(List.of(new Word("\nSecond"), new Word("sentence")))
                                )
                        )
                ),
                Arguments.of("It's header\n\nFirst sentence.", new Text(new Sentence(List.of(new Word("It's"), new Word("header"))),
                                List.of(new Sentence(List.of(new Word("First"), new Word("sentence"))))
                        )
                )
        );
    }

    public Stream<Arguments> negativeDataForCreateText() {
        return Stream.of(
                Arguments.of("It's header\nFirst sentence.\nSecond sentence."),
                Arguments.of("It's header. First sentence.\nSecond sentence."),
                Arguments.of("It's header. First sentence. Second sentence."),
                Arguments.of("It's header.\nFirst sentence. Second sentence."),
                Arguments.of("It's header.\n\n")
        );
    }
}
