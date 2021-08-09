package by.training.arrays.service.impl;

import by.training.arrays.entity.Matrix;
import by.training.arrays.service.exception.ServiceException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.List;

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
    public static Object[][] createPositiveDataForCreateFromFile() {
        return new Object[][]{
                {
                        Path.of("src", "test", "resources", "matrix", "creator", "positive", "01-positive-data-for-CreateFromFile.txt"),
                        List.of(new Matrix(
                                        new int[][]{{1, 2}, {3, 4}}
                                )
                        )
                },
                {
                        Path.of("src", "test", "resources", "matrix", "creator", "positive", "02-positive-data-for-CreateFromFile.txt"),
                        List.of(new Matrix(
                                        new int[][]{{1, 2}, {3, 4}}
                                )
                        )
                },
                {
                        Path.of("src", "test", "resources", "matrix", "creator", "positive", "03-positive-data-for-CreateFromFile.txt"),
                        List.of(new Matrix(
                                        new int[][]{{1, 2}, {3, 4}}
                                ),
                                new Matrix(
                                        new int[][]{{1, 2, 3}, {4, 5, 6}}
                                )
                        )
                },
                {
                        Path.of("src", "test", "resources", "matrix", "creator", "positive", "04-positive-data-for-CreateFromFile.txt"),
                        List.of(new Matrix(
                                        new int[][]{{1, 2}, {3, 4}}
                                ),
                                new Matrix(
                                        new int[][]{{1, 2, 3}, {4, 5, 6}}
                                )
                        )
                },
                {
                        Path.of("src", "test", "resources", "matrix", "creator", "positive", "05-positive-data-for-CreateFromFile.txt"),
                        List.of(new Matrix(
                                        new int[][]{{1, 2}, {3, 4}}
                                ),
                                new Matrix(
                                        new int[][]{{1, 2, 3}, {4, 5, 6}}
                                ),
                                new Matrix(
                                        new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}
                                )
                        )
                },
                {
                        Path.of("src", "test", "resources", "matrix", "creator", "positive", "07-positive-data-for-CreateFromFile.txt"),
                        List.of(new Matrix(
                                        new int[][]{{1}}
                                )
                        )
                },
                {
                        Path.of("src", "test", "resources", "matrix", "creator", "positive", "08-positive-data-for-CreateFromFile.txt"),
                        List.of(new Matrix(
                                        new int[][]{{1, 2}}
                                )
                        )
                },
                {
                        Path.of("src", "test", "resources", "matrix", "creator", "positive", "09-positive-data-for-CreateFromFile.txt"),
                        List.of(new Matrix(
                                        new int[][]{{1}, {2}}
                                )
                        )
                }
        };
    }

    @DataProvider
    public static Object[][] negativeDataForCreateFromFile() {
        return new Object[][]{
                {Path.of("invalidPath.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "01-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "02-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "03-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "04-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "05-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "06-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "07-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "08-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "09-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "10-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "11-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "12-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "13-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "14-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "15-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "16-negative-data-for-CreateFromFile.txt")},
                {Path.of("src", "test", "resources", "matrix", "creator", "negative", "17-negative-data-for-CreateFromFile.txt")}
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
