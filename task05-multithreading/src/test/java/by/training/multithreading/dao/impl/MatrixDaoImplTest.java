package by.training.multithreading.dao.impl;

import by.training.multithreading.dao.DaoException;
import by.training.multithreading.entity.Matrix;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.stream.StreamSupport;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class MatrixDaoImplTest {

    private MatrixDaoImpl matrixDao;

    @DataProvider(name = "positiveDataForRead")
    public static Iterator<Object[]> createPositiveDataForRead() throws IOException {
        Path parentDir = Path.of("src/test/resources/matrix/positive");
        DirectoryStream<Path> children = Files.newDirectoryStream(parentDir);
        Queue<Matrix> queue = new ArrayDeque<>(
                List.of(
                        new Matrix(new int[][]{{1}}),
                        new Matrix(new int[][]{{1, 2}}),
                        new Matrix(new int[][]{{1, 2}, {3, 4}}),
                        new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}),
                        new Matrix(new int[][]{{1}, {2}, {3}, {4}}),
                        new Matrix(new int[][]{{1, 2, 3, 4}}),
                        new Matrix(new int[][]{{-4, 54, -35, 18}, {4, 5, -16, -60}, {108, 39, -50, 90}}),
                        new Matrix(new int[][]{{1, -1, 1}, {-2, 2, -2}, {3, -3, 3}, {-4, 4, -4}})
                )
        );
        return StreamSupport.stream(children.spliterator(), false)
                .map(path -> new Object[]{path, queue.remove()})
                .iterator();
    }

    @DataProvider(name = "negativeDataForRead")
    public static Iterator<Object[]> createNegativeDataForRead() throws IOException {
        Path parentDir = Path.of("src/test/resources/matrix/negative");
        DirectoryStream<Path> children = Files.newDirectoryStream(parentDir);
        return StreamSupport.stream(children.spliterator(), false)
                .map(path -> new Object[]{path})
                .iterator();
    }

    @BeforeClass
    public void setUp() {
        matrixDao = new MatrixDaoImpl();
    }

    @Test(description = "test positive data for read method",
            dataProvider = "positiveDataForRead")
    public void testPositiveDataForRead(Path path, Matrix expected) {
        try {
            Matrix actual = matrixDao.read(path);
            assertEquals(actual, expected,
                    "invalid matrix"
            );
        } catch (DaoException e) {
            fail("dao exception occurred", e);
        }
    }

    @Test(description = "test negative data for read method",
            dataProvider = "negativeDataForRead",
            expectedExceptions = DaoException.class)
    public void testNegativeDataForRead(Path path) throws DaoException {
        matrixDao.read(path);
        fail("expected " + DaoException.class.getName());
    }

    @Test(description = "test invalid path for read method",
            expectedExceptions = DaoException.class)
    public void testInvalidPathForRead() throws DaoException {
        Path path = Path.of("invalid", "path", "to", "data.txt");
        matrixDao.read(path);
        fail("expected " + DaoException.class.getName());
    }

    @AfterClass
    public void tearDown() {
        matrixDao = null;
    }
}
