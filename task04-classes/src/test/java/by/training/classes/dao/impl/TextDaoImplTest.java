package by.training.classes.dao.impl;

import by.training.classes.dao.DaoException;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TextDaoImplTest {

    private TextDaoImpl dao;

    @BeforeAll
    void setUp() {
        dao = new TextDaoImpl();
    }

    @Test
    @Tag("save")
    void mustCreateNewFileIfFileDoesntExist() throws DaoException, IOException {
        Path path = Path.of("src/test/resources/save", "noSuchFile.txt");
        dao.save(path, "It's header.\n\nFirst sentence. Second sentence.");
        assertTrue(Files.exists(path), "must create new file");
        Files.deleteIfExists(path);
    }

    @Test
    @Tag("save")
    void mustRewriteFileThatAlreadyExist() throws DaoException, IOException {
        Path path = Path.of("src/test/resources/save", "existedFile.txt");
        String existedText = "It's header.\n\nFirst sentence. Second sentence.";
        Files.write(path, existedText.getBytes());

        String expected = "It's new header.\n\nNew first sentence.";
        dao.save(path, expected);

        String actual = Files.readString(path);
        assertEquals(expected, actual, "must rewrite existing file");
        Files.deleteIfExists(path);
    }

    @Test
    @Tag("read")
    void mustReadExistingFile() throws DaoException {
        Path path = Path.of("src/test/resources/read", "existedFile.txt");
        String expected = """
                It's header

                First sentence. Second sentence.
                Third sentence.""";

        String actual = dao.read(path);
        assertEquals(expected, actual, "must read text from file");
    }

    @Test
    @Tag("read")
    void mustThrowDaoExceptionIfNoSuchFileForRead() {
        Path path = Path.of("src/test/resources/read", "noSuchFile.txt");
        assertThrows(DaoException.class, () -> dao.read(path));
    }

    @Test
    @Tag("delete")
    void mustThrowDaoExceptionIfNoSuchFileForDelete() {
        Path path = Path.of("src/test/resources/delete", "noSuchFile.txt");
        assertThrows(DaoException.class, () -> dao.delete(path));
    }

    @Test
    @Tag("delete")
    void mustDeleteFileIfItExists() throws IOException, DaoException {
        Path path = Path.of("src/test/resources/delete", "mustBeDeleted.txt");
        Files.createFile(path);
        dao.delete(path);
        assertFalse(Files.exists(path));
    }

    @AfterAll
    void tearDown() {
        dao = null;
    }
}