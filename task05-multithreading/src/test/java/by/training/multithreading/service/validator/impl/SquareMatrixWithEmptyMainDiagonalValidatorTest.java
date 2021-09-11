package by.training.multithreading.service.validator.impl;

import by.training.multithreading.entity.Matrix;
import by.training.multithreading.service.validator.Validator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SquareMatrixWithEmptyMainDiagonalValidatorTest {

    private final Validator<Matrix> validator = SquareMatrixWithEmptyMainDiagonalValidator.getInstance();

    @DataProvider(name = "validDataForIsValid")
    public static Object[][] createValidDataForIsValid() {
        return new Object[][]{
                {new Matrix(new int[][]{{0, 2}, {1, 0}})},
                {new Matrix(new int[][]{{0, 1, 3}, {1, 0, 3}, {1, 2, 0}})},
                {new Matrix(new int[][]{{0, 2, 3, 4}, {1, 0, 3, 4}, {1, 2, 0, 4}, {1, 2, 3, 0}})},
                {new Matrix(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}})},
                {new Matrix(new int[][]{{0, 1, 2, 3, 4}, {1, 0, 2, 3, 4}, {1, 2, 0, 3, 4}, {1, 2, 3, 0, 4}, {1, 2, 3, 4, 0}})}
        };
    }

    @DataProvider(name = "invalidDataForIsValid")
    public static Object[][] createInvalidDataForIsValid() {
        return new Object[][]{
                {null},
                {new Matrix(new int[][]{{0}})},
                {new Matrix(new int[][]{{10}})},
                {new Matrix(new int[][]{{10, 2}, {1, 10}})},
                {new Matrix(new int[][]{{10, 1, 3}, {1, 10, 3}, {1, 2, 10}})},
                {new Matrix(new int[][]{{10, 2, 3, 4}, {1, 10, 3, 4}, {1, 2, 10, 4}, {1, 2, 3, 10}})},
                {new Matrix(new int[][]{{0, 2, 3, 4}, {1, 0, 3, 4}, {1, 2, 0, 4}})},
                {new Matrix(new int[][]{{-1, 2, 3}, {1, 0, 3}, {1, 2, 0}})},
                {new Matrix(new int[][]{{0, 2, 3}, {1, -1, 3}, {1, 2, 0}})},
                {new Matrix(new int[][]{{0, 2, 3}, {1, 0, 3}, {1, 2, -5}})},
                {new Matrix(new int[][]{{0, 1}})},
                {new Matrix(new int[][]{{0, 1}, {0, 1}})},
                {new Matrix(new int[][]{{0, 1, 3}, {1, 0, 3}, {1, 2, 0}, {0, 0, 0}})},
                {new Matrix(new int[][]{{0, 1, 2, 3, 4, 5}, {1, 0, 2, 3, 4, 5}, {1, 2, 0, 3, 4, 5}, {1, 2, 3, 0, 4, 5}, {1, 2, 3, 4, 0, 5}, {1, 2, 3, 4, 5, 0}})},
                {new Matrix(new int[][]{{0, 1, 2, 3, 4, 5, 6}, {1, 0, 2, 3, 4, 5, 6}, {1, 2, 0, 3, 4, 5, 6}, {1, 2, 3, 0, 4, 5, 6}, {1, 2, 3, 4, 0, 5, 6}, {1, 2, 3, 4, 5, 0, 6}, {1, 2, 3, 4, 5, 6, 0}})}
        };
    }

    @Test(description = "test valid data for isValid method",
            dataProvider = "validDataForIsValid")
    public void testValidDataForIsValid(Matrix matrix) {
        boolean actual = validator.isValid(matrix);
        assertTrue(actual);
    }

    @Test(description = "test invalid data for isValid method",
            dataProvider = "invalidDataForIsValid")
    public void testInvalidDataForIsValid(Matrix matrix) {
        boolean actual = validator.isValid(matrix);
        assertFalse(actual);
    }
}
