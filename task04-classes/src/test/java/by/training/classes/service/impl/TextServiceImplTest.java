package by.training.classes.service.impl;

import by.training.classes.entity.Sentence;
import by.training.classes.entity.Text;
import by.training.classes.entity.Word;
import by.training.classes.service.ServiceException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TextServiceImplTest {

    private TextServiceImpl service;
    private Text sourceText;

    @BeforeAll
    void setUp() {
        service = new TextServiceImpl();
    }

    @BeforeEach
    void setUpText() {
        sourceText = new Text(new Sentence(List.of(new Word("It's"), new Word("header"))),
                List.of(
                        new Sentence(List.of(new Word("First"), new Word("sentence"))),
                        new Sentence(List.of(new Word("Second"), new Word("sentence"))),
                        new Sentence(List.of(new Word("\nThird"), new Word("sentence")))
                )
        );
    }

    @Test
    @Tag("readText")
    void mustReadTextFromAFile() throws ServiceException {
        Path path = Path.of("src/test/resources/read", "existedFile.txt");
        Text actual = service.readText(path);
        assertEquals(sourceText, actual, "must read Text from file");
    }

    @ParameterizedTest
    @MethodSource("invalidDataForReadText")
    @NullSource
    @Tag("readText")
    void mustThrowServiceExceptionForInvalidInputForReadText(Path path) {
        assertThrows(ServiceException.class,
                () -> service.readText(path),
                "must throw Service exception for invalid data"
        );
    }

    @Test
    @Tag("saveText")
    void mustSaveTextToTheFile() throws ServiceException, IOException {
        Path pathOfSourceText = Path.of("src/test/resources/read", "existedFile.txt");
        Path pathOfNewText = Path.of("src/test/resources/read", "newText.txt");
        service.saveText(pathOfNewText, sourceText);
        List<String> expected = Files.readAllLines(pathOfSourceText);
        List<String> actual = Files.readAllLines(pathOfNewText);
        assertEquals(expected, actual, "must save Text to the file");
        Files.deleteIfExists(pathOfNewText);
    }

    @ParameterizedTest
    @MethodSource("nullDataForSaveText")
    @Tag("saveText")
    void mustThrowServiceExceptionForInvalidInputForSaveText(Path path, Text text) {
        assertThrows(ServiceException.class,
                () -> service.saveText(path, text),
                "must throw Service exception for invalid data"
        );
    }

    @Test
    @Tag("deleteText")
    void mustDeleteFileForValidData() throws IOException, ServiceException {
        Path path = Path.of("src/test/resources/delete", "something.txt");
        Files.createFile(path);
        service.deleteText(path);
        assertFalse(Files.exists(path), "file must be deleted");
    }

    @ParameterizedTest
    @MethodSource("invalidDataForDeleteText")
    @Tag("deleteText")
    void mustThrowExceptionForInvalidDataForDeleteText(Path path) {
        assertThrows(ServiceException.class,
                () -> service.deleteText(path),
                "must throw Service exception for invalid data"
        );
    }

    @AfterEach
    void tearDownText() {
        sourceText = null;
    }

    @AfterAll
    void tearDown() {
        service = null;
    }

    public Stream<Arguments> invalidDataForReadText() throws IOException {
        Path parent = Path.of("src/test/resources/read/invalid");
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(parent);
        return StreamSupport.stream(directoryStream.spliterator(), false)
                .map(Arguments::of);
    }

    public Stream<Arguments> nullDataForSaveText() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(Path.of("src/test/resources/save", "newText.txt"), null),
                Arguments.of(null, sourceText)
        );
    }

    public Stream<Arguments> invalidDataForDeleteText() {
        return Stream.of(
                Arguments.of((Path) null),
                Arguments.of(Path.of("invalid", "path", "to", "file.txt"))
        );
    }
}
