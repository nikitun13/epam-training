package by.training.classes.service.impl;

import by.training.classes.entity.Sentence;
import by.training.classes.entity.Word;
import by.training.classes.service.ServiceException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SentenceCreatorServiceImplTest {

    private SentenceCreatorServiceImpl service;

    @BeforeAll
    void setUp() {
        service = new SentenceCreatorServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("testDataForCreateSentence")
    void createSentenceIfValidDataReceived(String value, Sentence expected) throws ServiceException {
        Sentence actual = service.createSentence(value);
        assertEquals(expected, actual,
                () -> "must create Sentence: " + expected + " for string: " + value
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("negativeDataForCreateSentence")
    @Tag("createSentence")
    void throwServiceExceptionIfInputIsInvalid(String value) {
        assertThrows(ServiceException.class,
                () -> service.createSentence(value),
                () -> "must throw " + ServiceException.class.getName() + " for invalid input data"
        );
    }

    @AfterAll
    void tearDown() {
        service = null;
    }

    public Stream<Arguments> testDataForCreateSentence() {
        return Stream.of(
                Arguments.of("Hello from", new Sentence(List.of(new Word("Hello"), new Word("from")))),
                Arguments.of("Hello         from", new Sentence(List.of(new Word("Hello"), new Word("from")))),
                Arguments.of("Hello \nfrom", new Sentence(List.of(new Word("Hello"), new Word("\nfrom")))),
                Arguments.of("Hello", new Sentence(List.of(new Word("Hello")))),
                Arguments.of("\nHello", new Sentence(List.of(new Word("\nHello")))),
                Arguments.of(" Hello", new Sentence(List.of(new Word("Hello"))))
        );
    }

    public Stream<Arguments> negativeDataForCreateSentence() {
        return Stream.of(
                Arguments.of("  Hello"),
                Arguments.of("  Hello   world"),
                Arguments.of("  Hello world"),
                Arguments.of("          Hello      world   "),
                Arguments.of("          Hello world   "),
                Arguments.of("          Hello   ")
        );
    }
}
