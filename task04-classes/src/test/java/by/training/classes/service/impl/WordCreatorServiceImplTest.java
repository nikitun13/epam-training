package by.training.classes.service.impl;

import by.training.classes.entity.Word;
import by.training.classes.service.ServiceException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WordCreatorServiceImplTest {

    private WordCreatorServiceImpl service;

    @BeforeAll
    void setUp() {
        service = new WordCreatorServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("testDataForCreateWord")
    @Tag("createWord")
    void createWordIfValidDataReceived(String value, Word expected) throws ServiceException {
        Word actual = service.createWord(value);
        assertEquals(expected, actual,
                () -> "must create Word: " + expected + " for string: " + value
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @Tag("createWord")
    void throwServiceExceptionIfInputIsBlankOrNull(String value) {
        assertThrows(ServiceException.class,
                () -> service.createWord(value),
                () -> "must throw " + ServiceException.class.getName() + " for blank or null data"
        );
    }

    @AfterAll
    void tearDown() {
        service = null;
    }

    public Stream<Arguments> testDataForCreateWord() {
        return Stream.of(
                Arguments.of("String", new Word("String")),
                Arguments.of("car", new Word("car")),
                Arguments.of("дом", new Word("дом"))
        );
    }
}
