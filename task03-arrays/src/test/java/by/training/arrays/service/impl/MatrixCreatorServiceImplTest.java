package by.training.arrays.service.impl;

import by.training.arrays.entity.Matrix;
import by.training.arrays.service.exception.ServiceException;
import org.testng.annotations.*;

import static org.testng.Assert.*;

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

    @DataProvider
    public static Object[][] negativeDataForFillRandomized() {
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

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
