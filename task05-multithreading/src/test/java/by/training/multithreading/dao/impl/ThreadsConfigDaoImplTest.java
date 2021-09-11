package by.training.multithreading.dao.impl;

import by.training.multithreading.dao.DaoException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.StreamSupport;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ThreadsConfigDaoImplTest {

    private ThreadsConfigDaoImpl dao;

    @DataProvider(name = "positiveDataForReadConfigs")
    public static Iterator<Object[]> createPositiveDataForReadConfigs() throws IOException {
        Path parentDir = Path.of("src/test/resources/thread/positive");
        DirectoryStream<Path> children = Files.newDirectoryStream(parentDir);
        Queue<List<Integer>> queue = new ArrayDeque<>(
                List.of(
                        Collections.emptyList(),
                        List.of(1),
                        List.of(1, 2),
                        List.of(1, 2, 3),
                        List.of(1, 2, 3, 132, 4, 56, 87, 43, 34),
                        List.of(-1, -2, -3, -132, -4, -56, -87, -43, -34),
                        List.of(-1)
                )
        );
        return StreamSupport.stream(children.spliterator(), false)
                .map(path -> new Object[]{path, queue.remove()})
                .iterator();
    }

    @DataProvider(name = "negativeDataForReadConfigs")
    public static Iterator<Object[]> createNegativeDataForReadConfigs() throws IOException {
        Path parentDir = Path.of("src/test/resources/thread/negative");
        DirectoryStream<Path> children = Files.newDirectoryStream(parentDir);
        return StreamSupport.stream(children.spliterator(), false)
                .map(path -> new Object[]{path})
                .iterator();
    }

    @BeforeClass
    public void setUp() {
        dao = new ThreadsConfigDaoImpl();
    }

    @Test(description = "test positive data for readConfigs method",
            dataProvider = "positiveDataForReadConfigs")
    public void testPositiveDataReadConfigs(Path path, List<Integer> expected) throws DaoException {
        List<Integer> actual = dao.readConfigs(path);
        assertEquals(actual, expected);
    }

    @Test(description = "test negative data for readConfigs method",
            dataProvider = "negativeDataForReadConfigs",
            expectedExceptions = DaoException.class)
    public void testNegativeDataReadConfigs(Path path) throws DaoException {
        dao.readConfigs(path);
        fail("expected " + DaoException.class.getName());
    }

    @Test(description = "test invalid path for readConfigs method",
            expectedExceptions = DaoException.class)
    public void testInvalidPathForRead() throws DaoException {
        Path path = Path.of("invalid", "path", "to", "data.txt");
        dao.readConfigs(path);
        fail("expected " + DaoException.class.getName());
    }

    @AfterClass
    public void tearDown() {
        dao = null;
    }
}
