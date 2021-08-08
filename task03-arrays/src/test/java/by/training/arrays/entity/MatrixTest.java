package by.training.arrays.entity;

import by.training.arrays.entity.exception.MatrixException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MatrixTest {

    @DataProvider(name = "positiveDataForArrayConstructor")
    public static Object[][] positiveDataForArrayConstructor() {
        return new Object[][]{
                {new int[1][1]},
                {new int[2][3]},
                {new int[1][5]},
                {new int[5][1]},
                {new int[3][2]},
                {new int[][]{{1, 2, 3}}},
                {new int[][]{{1, 2, 3}, {4, 5, 6}}},
                {new int[][]{{1, 2, 3}, {4, 5, 6}, {46, 22, 43}}}
        };
    }

    @DataProvider(name = "negativeDataForArrayConstructor")
    public static Object[][] createNegativeDataForArrayConstructor() {
        return new Object[][]{
                {new int[][]{}},
                {new int[][]{{}, {}, {}}},
                {new int[][]{{1, 3}, {1}, {5}}},
                {new int[][]{{1}, {1, 3}, {5}}},
                {new int[][]{{1}, {1, 3}, {5, 6}}},
                {new int[][]{{}, {1, 3}, {5, 6}}},
                {new int[][]{{}, {1}, {5, 6}}},
                {new int[][]{{}, {1}, {5}}},
                {new int[][]{{1, 3}, {}, {}}},
                {new int[][]{{1, 3}, {1, 4}, {}}},
                {new int[][]{{1, 3}, {}, {}}},
                {new int[0][0]},
                {new int[1][0]},
                {new int[0][1]},
                {new int[0][5]},
                {new int[5][0]},
                {new int[0][]}
        };
    }

    @DataProvider(name = "nullDataForArrayConstructor")
    public static Object[][] createNullDataForArrayConstructor() {
        return new Object[][]{
                {null},
                {new int[1][]},
                {new int[5][]},
                {new int[][]{null}},
                {new int[][]{null, null, null}},
                {new int[][]{{4, 5}, null}},
                {new int[][]{null, {4, 5}}},
                {new int[][]{{4, 5}, {2, 7}, null, {8, 4}}}
        };
    }

    @DataProvider(name = "positiveDataForSizeConstructor")
    public static Object[][] createPositiveDataForSizeConstructor() {
        return new Object[][]{
                {1, 1},
                {2, 2},
                {1, 5},
                {5, 1},
                {5, 5}
        };
    }

    @DataProvider(name = "negativeDataForSizeConstructor")
    public static Object[][] createNegativeDataForSizeConstructor() {
        return new Object[][]{
                {0, 0},
                {-1, -1},
                {-1, 0},
                {0, -1},
                {0, 1},
                {1, 0},
                {-5, 0},
                {0, -5},
                {1, -5},
                {1, -1}
        };
    }

    @DataProvider(name = "positiveDataForGetElement")
    public static Object[][] createPositiveDataForGetElement() {
        return new Object[][]{};
    }

    @Test(description = "test positive scenario for creation Matrix entity using array",
            dataProvider = "positiveDataForArrayConstructor")
    public void testPositiveScenarioArrayConstructor(int[][] elements) {
        Matrix matrix = new Matrix(elements);
        assertNotNull(matrix, "must create Matrix");
    }

    @Test(description = "test negative scenario for creation Matrix entity using array",
            dataProvider = "negativeDataForArrayConstructor",
            expectedExceptions = MatrixException.class)
    public void testNegativeScenarioArrayConstructor(int[][] elements) {
        new Matrix(elements);
        fail("Must throw " + MatrixException.class.getName() + " for invalid input");
    }

    @Test(description = "test null scenario for creation Matrix entity using array",
            dataProvider = "nullDataForArrayConstructor",
            expectedExceptions = NullPointerException.class)
    public void testNullScenarioArrayConstructor(int[][] elements) {
        new Matrix(elements);
        fail("Must throw " + NullPointerException.class.getName() + " for null input");
    }

    @Test(description = "test positive scenario for creation Matrix entity using size",
            dataProvider = "positiveDataForSizeConstructor")
    public void testPositiveScenarioSizeConstructor(int rows, int columns) {
        Matrix matrix = new Matrix(rows, columns);
        assertNotNull(matrix, String.format(
                "must create Matrix for rows = %d and columns = %d on input",
                rows, columns)
        );
    }

    @Test(description = "test negative scenario for creation Matrix entity using size",
            dataProvider = "negativeDataForSizeConstructor",
            expectedExceptions = MatrixException.class)
    public void testNegativeScenarioSizeConstructor(int rows, int columns) {
        new Matrix(rows, columns);
        fail(String.format("Must throw %s for rows = %d and columns = %d on input",
                        MatrixException.class.getName(), rows, columns
                )
        );
    }
}
