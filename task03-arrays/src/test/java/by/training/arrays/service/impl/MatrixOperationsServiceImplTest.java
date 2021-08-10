package by.training.arrays.service.impl;

import by.training.arrays.entity.Matrix;
import by.training.arrays.service.MatrixCreatorService;
import by.training.arrays.service.exception.ServiceException;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.StreamSupport;

import static org.testng.Assert.*;

public class MatrixOperationsServiceImplTest {

    private static final MatrixCreatorService creator = new MatrixCreatorServiceImpl();

    private MatrixOperationsServiceImpl service;

    @DataProvider(name = "positiveDataForMultiplyTwoMatrix")
    public static Iterator<Object[]> createPositiveDataForMultiplyTwoMatrix() throws IOException {
        Path parent = Path.of("src", "test", "resources", "operations", "multiplyTwoMatrix", "positive");
        DirectoryStream<Path> children = Files.newDirectoryStream(parent);
        return StreamSupport.stream(children.spliterator(), false)
                .map(creator::createFromFile)
                .map(list -> new Object[]{list.get(0), list.get(1), list.get(2)})
                .iterator();
    }

    @DataProvider(name = "negativeDataForMultiplyTwoMatrix")
    public static Object[][] createNegativeDataForMultiplyTwoMatrix() {
        return new Object[][]{
                {new Matrix(1, 1), new Matrix(2, 1)},
                {new Matrix(2, 1), new Matrix(2, 1)},
                {new Matrix(3, 2), new Matrix(3, 3)},
                {new Matrix(3, 3), new Matrix(2, 3)},
                {new Matrix(2, 1), new Matrix(2, 3)},
                {new Matrix(4, 2), new Matrix(4, 2)},
                {new Matrix(2, 1), new Matrix(1, 2)}
        };
    }

    @DataProvider(name = "twoNullMatrixData")
    public static Object[][] createTwoNullMatrixData() {
        return new Object[][]{
                {null, null},
                {null, new Matrix(1, 1)},
                {new Matrix(1, 1), null}
        };
    }

    @DataProvider(name = "positiveDataForMultiplyByCoefficient")
    public static Iterator<Object[]> createPositiveDataForMultiplyByCoefficient() throws IOException {
        Path parent = Path.of("src", "test", "resources", "operations", "multiplyByCoefficient", "positive");
        DirectoryStream<Path> children = Files.newDirectoryStream(parent);
        Queue<Integer> queue = new ArrayDeque<>(
                List.of(
                        1,
                        0,
                        -1,
                        2,
                        -2,
                        5,
                        -5,
                        9,
                        10,
                        2,
                        100,
                        7
                )
        );
        return StreamSupport.stream(children.spliterator(), false)
                .map(creator::createFromFile)
                .map(list -> new Object[]{queue.remove(), list.get(0), list.get(1)})
                .iterator();
    }

    @DataProvider(name = "positiveDataForAdd")
    public static Iterator<Object[]> createPositiveDataForAdd() throws IOException {
        Path parent = Path.of("src", "test", "resources", "operations", "add", "positive");
        DirectoryStream<Path> children = Files.newDirectoryStream(parent);
        return StreamSupport.stream(children.spliterator(), false)
                .map(creator::createFromFile)
                .map(list -> new Object[]{list.get(0), list.get(1), list.get(2)})
                .iterator();
    }

    @DataProvider(name = "negativeDataForAddAndSubtract")
    public static Object[][] createNegativeDataForAddAndSubtract() {
        return new Object[][]{
                {new Matrix(new int[1][1]), new Matrix(new int[2][2])},
                {new Matrix(new int[1][1]), new Matrix(new int[1][2])},
                {new Matrix(new int[1][1]), new Matrix(new int[2][1])},
                {new Matrix(new int[1][2]), new Matrix(new int[1][1])},
                {new Matrix(new int[2][1]), new Matrix(new int[1][1])},
                {new Matrix(new int[2][2]), new Matrix(new int[1][1])},
                {new Matrix(new int[3][5]), new Matrix(new int[5][3])},
                {new Matrix(new int[5][3]), new Matrix(new int[3][5])},
                {new Matrix(new int[1][2]), new Matrix(new int[3][4])},
                {new Matrix(new int[3][4]), new Matrix(new int[1][2])},
                {new Matrix(new int[7][7]), new Matrix(new int[5][5])},
                {new Matrix(new int[5][5]), new Matrix(new int[7][7])},
                {new Matrix(new int[3][4]), new Matrix(new int[3][5])},
                {new Matrix(new int[3][4]), new Matrix(new int[5][3])}
        };
    }

    @DataProvider(name = "positiveDataForSubtract")
    public static Iterator<Object[]> createPositiveDataForSubtract() throws IOException {
        Path parent = Path.of("src", "test", "resources", "operations", "subtract", "positive");
        DirectoryStream<Path> children = Files.newDirectoryStream(parent);
        return StreamSupport.stream(children.spliterator(), false)
                .map(creator::createFromFile)
                .map(list -> new Object[]{list.get(0), list.get(1), list.get(2)})
                .iterator();
    }

    @DataProvider(name = "positiveDataForTranspose")
    public static Iterator<Object[]> createPositiveDataForTranspose() throws IOException {
        Path parent = Path.of("src", "test", "resources", "operations", "transpose", "positive");
        DirectoryStream<Path> children = Files.newDirectoryStream(parent);
        return StreamSupport.stream(children.spliterator(), false)
                .map(creator::createFromFile)
                .map(list -> new Object[]{list.get(0), list.get(1)})
                .iterator();
    }

    @BeforeClass
    public void setUp() {
        service = new MatrixOperationsServiceImpl();
    }

    @Test(description = "test positive scenario for multiply method for two matrix",
            dataProvider = "positiveDataForMultiplyTwoMatrix")
    public void testPositiveScenarioMultiplyTwoMatrix(Matrix left, Matrix right, Matrix expected) {
        Matrix actual = service.multiplyTwoMatrix(left, right);
        assertEquals(actual, expected, "product must be " + expected);
    }

    @Test(description = "test negative scenario for multiply method for two matrix",
            dataProvider = "negativeDataForMultiplyTwoMatrix",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioMultiplyTwoMatrix(Matrix left, Matrix right) {
        service.multiplyTwoMatrix(left, right);
        fail("must throw: " + ServiceException.class.getName());
    }

    @Test(description = "test null data for multiply method for two matrix",
            dataProvider = "twoNullMatrixData",
            expectedExceptions = NullPointerException.class)
    public void testNullDataForMultiplyTwoMatrix(Matrix left, Matrix right) {
        service.multiplyTwoMatrix(left, right);
        fail("must throw: " + NullPointerException.class.getName());
    }

    @Test(description = "test positive scenario for multiply method by coefficient",
            dataProvider = "positiveDataForMultiplyByCoefficient")
    public void testPositiveScenarioMultiplyByCoefficient(int coefficient, Matrix matrix, Matrix expected) {
        Matrix actual = service.multiplyByCoefficient(coefficient, matrix);
        assertEquals(actual, expected, "product must be " + expected);
    }

    @Test(description = "test matrix is null scenario for multiply method by coefficient",
            expectedExceptions = NullPointerException.class)
    public void testMatrixIsNullScenarioMultiplyByCoefficient() {
        service.multiplyByCoefficient(2, null);
        fail("must throw " + NullPointerException.class.getName());
    }

    @Test(description = "test positive data for add method",
            dataProvider = "positiveDataForAdd")
    public void testPositiveScenarioAdd(Matrix left, Matrix right, Matrix expected) {
        Matrix actual = service.add(left, right);
        assertEquals(actual, expected,
                "the result of the addition must be: " + expected
        );
    }

    @Test(description = "test negative data for add method",
            dataProvider = "negativeDataForAddAndSubtract",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioAdd(Matrix left, Matrix right) {
        service.add(left, right);
        fail("must throw " + ServiceException.class.getName());
    }

    @Test(description = "test null data for add method",
            dataProvider = "twoNullMatrixData",
            expectedExceptions = NullPointerException.class)
    public void testNullInputScenarioAdd(Matrix left, Matrix right) {
        service.add(left, right);
        fail("must throw " + NullPointerException.class.getName());
    }

    @Test(description = "test positive data for subtract method",
            dataProvider = "positiveDataForSubtract")
    public void testPositiveScenarioSubtract(Matrix left, Matrix right, Matrix expected) {
        Matrix actual = service.subtract(left, right);
        assertEquals(actual, expected,
                "the result of the subtraction must be: " + expected
        );
    }

    @Test(description = "test negative data for subtract method",
            dataProvider = "negativeDataForAddAndSubtract",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioSubtract(Matrix left, Matrix right) {
        service.subtract(left, right);
        fail("must throw " + ServiceException.class.getName());
    }

    @Test(description = "test null data for subtract method",
            dataProvider = "twoNullMatrixData",
            expectedExceptions = NullPointerException.class)
    public void testNullInputScenarioSubtract(Matrix left, Matrix right) {
        service.subtract(left, right);
        fail("must throw " + NullPointerException.class.getName());
    }

    @Test(description = "test positive data for transpose method",
            dataProvider = "positiveDataForTranspose")
    public void testTranspose(Matrix matrix, Matrix expected) {
        Matrix actual = service.transpose(matrix);
        assertEquals(actual, expected,
                "transposed matrix must be: " + expected
        );
    }

    @Test(description = "test matrix is null scenario for transpose method",
            expectedExceptions = NullPointerException.class)
    public void testMatrixIsNullScenarioTranspose() {
        service.transpose(null);
        fail("must throw " + NullPointerException.class.getName());
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
