package by.training.information.service.impl;

import by.training.information.dao.DaoException;
import by.training.information.dao.reader.ReaderImpl;
import by.training.information.entity.TextComposite;
import by.training.information.service.ServiceException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TextServiceImplTest {

    private TextServiceImpl service;

    @BeforeAll
    void setUp() {
        service = new TextServiceImpl();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("invalidPath")
    @Tag("readTextFromFile")
    void shouldThrowServiceExceptionIfPathToFileIsInvalidForReadTextFromFile(String pathToFile) {
        assertThrows(ServiceException.class,
                () -> service.readTextFromFile(pathToFile),
                () -> "Must throw " + ServiceException.class
        );
    }

    @Test
    @Tag("readTextFromFile")
    void shouldThrowServiceExceptionIfPathToFileIsNotExistsForReadTextFromFile() {
        String pathToFile = "no/such/file.txt";

        assertThrows(ServiceException.class,
                () -> service.readTextFromFile(pathToFile),
                () -> "Must throw " + ServiceException.class
        );
    }

    @Test
    @Tag("readTextFromFile")
    void shouldReadAndCreateTextIfValidDataReceivedForReadTextFromFile() throws ServiceException, DaoException {
        String pathToFile = "src/test/resources/text/text.txt";
        Path path = Path.of(pathToFile);
        ReaderImpl reader = ReaderImpl.getInstance();
        String expected = String.join("\n", reader.readAllLines(path));

        TextComposite text = service.readTextFromFile(pathToFile);
        String actual = text.collect();

        assertEquals(expected, actual);
    }

    @AfterAll
    void tearDown() {
        service = null;
    }

    public Stream<Arguments> invalidPath() {
        return Stream.of(
                Arguments.of("48iojf|}{?oi&*((*.x343"),
                Arguments.of(">"),
                Arguments.of("<"),
                Arguments.of("!"),
                Arguments.of("."),
                Arguments.of(","),
                Arguments.of(".txt")
        );
    }
}