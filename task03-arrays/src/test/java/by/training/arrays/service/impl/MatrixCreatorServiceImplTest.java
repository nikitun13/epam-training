package by.training.arrays.service.impl;

import by.training.arrays.entity.Matrix;
import by.training.arrays.service.ServiceException;
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

public class MatrixCreatorServiceImplTest {

    MatrixCreatorServiceImpl service;

    @DataProvider(name = "positiveDataForFillRandomized")
    public static Object[][] createPositiveDataForFillRandomized() {
        return new Object[][]{
                {new Matrix(new int[10][10]), 0, 1},
                {new Matrix(new int[10][10]), 0, 5},
                {new Matrix(new int[10][10]), 2, 7},
                {new Matrix(new int[10][10]), -5, 0},
                {new Matrix(new int[10][10]), -7, -5},
                {new Matrix(new int[10][10]), -1, 0},
                {new Matrix(new int[10][10]), -1, 1},
                {new Matrix(new int[10][10]), -5, 5},
                {new Matrix(new int[10][10]), 0, 0},
                {new Matrix(new int[10][10]), 1, 1},
                {new Matrix(new int[10][10]), -1, -1},
                {new Matrix(new int[10][10]), -5, 5}
        };
    }

    @DataProvider(name = "negativeDataForFillRandomized")
    public static Object[][] createNegativeDataForFillRandomized() {
        return new Object[][]{
                {new Matrix(new int[10][10]), 1, 0},
                {new Matrix(new int[10][10]), 5, 0},
                {new Matrix(new int[10][10]), 7, 2},
                {new Matrix(new int[10][10]), 0, -5},
                {new Matrix(new int[10][10]), -5, -7},
                {new Matrix(new int[10][10]), 0, -1},
                {new Matrix(new int[10][10]), 1, -1},
                {new Matrix(new int[10][10]), 5, -5},
                {new Matrix(new int[10][10]), 5, -5}
        };
    }

    @DataProvider(name = "positiveDataForCreateFromFile")
    public static Iterator<Object[]> createPositiveDataForCreateFromFile() throws IOException {
        Path parent = Path.of("src", "test", "resources", "creator", "createFromFile", "positive");
        DirectoryStream<Path> children = Files.newDirectoryStream(parent);
        Queue<List<Matrix>> queue = new ArrayDeque<>(List.of(
                List.of(new Matrix(new int[][]{{1, 2}, {3, 4}})),
                List.of(new Matrix(new int[][]{{1, 2}, {3, 4}})),
                List.of(new Matrix(new int[][]{{1, 2}, {3, 4}}),
                        new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}})),
                List.of(new Matrix(new int[][]{{1, 2}, {3, 4}}),
                        new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}})),
                List.of(new Matrix(new int[][]{{1, 2}, {3, 4}}),
                        new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}}),
                        new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})),
                List.of(new Matrix(new int[][]{{1}})),
                List.of(new Matrix(new int[][]{{1, 2}})),
                List.of(new Matrix(new int[][]{{1}, {2}})),
                List.of(new Matrix(new int[][]{{1, 2}, {3, 4}})),
                List.of(new Matrix(new int[][]{{1, 2}, {3, 4}})),
                List.of(new Matrix(new int[][]{{1, 2}, {3, 4}})),
                List.of(new Matrix(new int[][]{{1, 2}, {3, 4}})),
                List.of(new Matrix(new int[][]{{1, 2}, {3, 4}}))
        ));
        return StreamSupport.stream(children.spliterator(), false)
                .map(path -> new Object[]{path, queue.remove()})
                .iterator();
    }

    @DataProvider
    public static Iterator<Object[]> negativeDataForCreateFromFile() throws IOException {
        Path parent = Path.of("src", "test", "resources", "creator", "createFromFile", "negative");
        DirectoryStream<Path> children = Files.newDirectoryStream(parent);
        return StreamSupport.stream(children.spliterator(), false)
                .map(path -> new Object[]{path})
                .iterator();
    }

    @BeforeClass
    public void setUp() {
        service = new MatrixCreatorServiceImpl();
    }

    @Test(description = "test positive scenario for fillRandomized method",
            dataProvider = "positiveDataForFillRandomized")
    public void testPositiveScenarioFillRandomized(Matrix matrix, int minValue, int maxValue) {
        service.fillRandomized(matrix, minValue, maxValue);
        System.out.println(matrix);
    }

    @Test(description = "test negative scenario for fillRandomized method",
            dataProvider = "negativeDataForFillRandomized",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioFillRandomized(Matrix matrix, int minValue, int maxValue) {
        service.fillRandomized(matrix, minValue, maxValue);
        fail(String.format("must throw %s for %d and %d on input",
                ServiceException.class.getName(), minValue, maxValue)
        );
    }

    @Test(description = "test matrix is null for fillRandomized method on input",
            expectedExceptions = NullPointerException.class)
    public void testNullMatrixForFillRandomized() {
        service.fillRandomized(null, 1, 2);
        fail("must throw "
                + NullPointerException.class.getName()
                + " for null matrix on input");
    }

    @Test(description = "test positive scenario for createFromFile method",
            dataProvider = "positiveDataForCreateFromFile")
    public void testPositiveScenarioCreateFromFile(Path path, List<Matrix> expected) {
        List<Matrix> actual = service.createFromFile(path);
        assertEquals(actual, expected);
    }

    @Test(description = "test negative scenario for createFromFile method",
            dataProvider = "negativeDataForCreateFromFile",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioCreateFromFile(Path path) {
        service.createFromFile(path);
        fail("must throw " + ServiceException.class.getName() + " for invalid data in the file");
    }

    @Test(description = "test path is null scenario for createFromFile method",
            expectedExceptions = NullPointerException.class)
    public void testPathIsNullScenarioCreateFromFile() {
        service.createFromFile(null);
        fail("must throw " + NullPointerException.class.getName() + " for null path on input");
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
