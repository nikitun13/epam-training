package by.training.information.dao.impl;

import by.training.information.dao.DaoException;
import by.training.information.dao.reader.ReaderImpl;
import by.training.information.entity.TextComposite;
import org.junit.jupiter.api.*;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TextDaoImplTest {

    private TextDaoImpl dao;

    @BeforeAll
    void setUp() {
        dao = new TextDaoImpl();
    }

    @Test
    @Tag("read")
    void shouldReadAndCreateText() throws DaoException {
        Path path = Path.of("src/test/resources/text/text.txt");
        ReaderImpl reader = ReaderImpl.getInstance();
        String expected = String.join("\n", reader.readAllLines(path));

        TextComposite text = dao.read(path);
        String actual = text.collect();

        assertEquals(expected, actual);
    }

    @AfterAll
    void tearDown() {
        dao = null;
    }
}
