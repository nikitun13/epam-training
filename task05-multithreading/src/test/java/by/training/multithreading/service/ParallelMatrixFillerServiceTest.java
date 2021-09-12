package by.training.multithreading.service;

import by.training.multithreading.entity.Matrix;
import by.training.multithreading.service.impl.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;


public class ParallelMatrixFillerServiceTest {

    private ParallelMatrixFillerService service;

    public ParallelMatrixFillerServiceTest() {
    }

    @Factory(dataProvider = "serviceImplementationData")
    public ParallelMatrixFillerServiceTest(ParallelMatrixFillerService service) {
        this.service = service;
    }

    @DataProvider(name = "serviceImplementationData")
    public Object[][] serviceImplementationData() {
        return new Object[][]{
                {new LockFillerService()},
                {new SemaphoreFillerService()},
                {new AtomicFillerService()},
                {new MapFillerService()},
                {new SetFillerService()}
        };
    }

    @DataProvider
    public static Object[][] negativeDataForFillMatrix() {
        return new Object[][]{
                {null, null},
                {null, "src/test/resources/thread/positive/01-positive-config.txt"},
                {new Matrix(new int[][]{{0}}), "src/test/resources/thread/positive/01-positive-config.txt"},
                {new Matrix(new int[][]{{10, 2}, {1, 10}}), "src/test/resources/thread/positive/02-positive-config.txt"},
                {new Matrix(new int[][]{{10, 1, 3}, {1, 10, 3}, {1, 2, 10}}), "src/test/resources/thread/positive/02-positive-config.txt"},
                {new Matrix(new int[][]{{10, 2, 3, 4}, {1, 10, 3, 4}, {1, 2, 10, 4}, {1, 2, 3, 10}}), "src/test/resources/thread/positive/03-positive-config.txt"},
                {new Matrix(new int[][]{{0, 2, 3, 4}, {1, 0, 3, 4}, {1, 2, 0, 4}}), "src/test/resources/thread/positive/04-positive-config.txt"},
                {new Matrix(new int[][]{{-1, 2, 3}, {1, 0, 3}, {1, 2, 0}}), "src/test/resources/thread/positive/04-positive-config.txt"},
                {new Matrix(new int[][]{{0, 2, 3}, {1, -1, 3}, {1, 2, 0}}), "src/test/resources/thread/positive/05-positive-config.txt"},
                {new Matrix(new int[][]{{0, 2, 3}, {1, 0, 3}, {1, 2, -5}}), "src/test/resources/thread/positive/05-positive-config.txt"},
                {new Matrix(new int[][]{{0, 1}}), "src/test/resources/thread/positive/05-positive-config.txt"},
                {new Matrix(new int[][]{{0, 1}, {0, 1}}), "src/test/resources/thread/positive/06-positive-config.txt"},
                {new Matrix(new int[][]{{0, 1, 3}, {1, 0, 3}, {1, 2, 0}, {0, 0, 0}}), "src/test/resources/thread/positive/06-positive-config.txt"},
                {new Matrix(new int[][]{{0, 1, 2, 3, 4, 5}, {1, 0, 2, 3, 4, 5}, {1, 2, 0, 3, 4, 5}, {1, 2, 3, 0, 4, 5}, {1, 2, 3, 4, 0, 5}, {1, 2, 3, 4, 5, 0}}), "src/test/resources/thread/positive/07-positive-config.txt"},
                {new Matrix(new int[][]{{0, 1, 2, 3, 4, 5, 6}, {1, 0, 2, 3, 4, 5, 6}, {1, 2, 0, 3, 4, 5, 6}, {1, 2, 3, 0, 4, 5, 6}, {1, 2, 3, 4, 0, 5, 6}, {1, 2, 3, 4, 5, 0, 6}, {1, 2, 3, 4, 5, 6, 0}}), "src/test/resources/thread/positive/07-positive-config.txt"},
                {new Matrix(new int[][]{{0, 2}, {1, 0}}), "src/test/resources/thread/negative/01-negative-config.txt"},
                {new Matrix(new int[][]{{0, 1, 3}, {1, 0, 3}, {1, 2, 0}}), "src/test/resources/thread/negative/02-negative-config.txt"},
                {new Matrix(new int[][]{{0, 2, 3, 4}, {1, 0, 3, 4}, {1, 2, 0, 4}, {1, 2, 3, 0}}), "src/test/resources/thread/negative/03-negative-config.txt"},
                {new Matrix(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}), "src/test/resources/thread/negative/04-negative-config.txt"},
                {new Matrix(new int[][]{{0, 1, 2, 3, 4}, {1, 0, 2, 3, 4}, {1, 2, 0, 3, 4}, {1, 2, 3, 0, 4}, {1, 2, 3, 4, 0}}), "src/test/resources/thread/negative/05-negative-config.txt"},
                {new Matrix(new int[][]{{0, 2}, {1, 0}}), "src/test/resources/thread/negative/06-negative-config.txt"},
                {new Matrix(new int[][]{{0, 1, 3}, {1, 0, 3}, {1, 2, 0}}), "src/test/resources/thread/negative/07-negative-config.txt"},
                {new Matrix(new int[][]{{0, 2, 3, 4}, {1, 0, 3, 4}, {1, 2, 0, 4}, {1, 2, 3, 0}}), "src/test/resources/thread/negative/08-negative-config.txt"},
                {new Matrix(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}), "src/test/resources/thread/negative/09-negative-config.txt"},
                {new Matrix(new int[][]{{0, 1, 2, 3, 4}, {1, 0, 2, 3, 4}, {1, 2, 0, 3, 4}, {1, 2, 3, 0, 4}, {1, 2, 3, 4, 0}}), "src/test/resources/thread/negative/10-negative-config.txt"},
                {new Matrix(new int[][]{{0, 2, 3, 4}, {1, 0, 3, 4}, {1, 2, 0, 4}, {1, 2, 3, 0}}), "src/test/resources/thread/negative/11-negative-config.txt"},
                {new Matrix(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}), "src/test/resources/thread/negative/12-negative-config.txt"},
                {new Matrix(new int[][]{{0, 1, 2, 3, 4}, {1, 0, 2, 3, 4}, {1, 2, 0, 3, 4}, {1, 2, 3, 0, 4}, {1, 2, 3, 4, 0}}), "src/test/resources/thread/negative/13-negative-config.txt"},
                {new Matrix(new int[][]{{0, 1, 3}, {1, 0, 3}, {1, 2, 0}}), "invalid/path/to/data.txt"},
                {new Matrix(new int[][]{{0, 2}, {1, 0}}), null}
        };
    }

    @Test(description = "test negative data for fillMatrix method",
            dataProvider = "negativeDataForFillMatrix",
            expectedExceptions = ServiceException.class)
    public void testNegativeDataFillMatrix(Matrix matrix, String path) throws ServiceException {
        service.fillMatrix(matrix, path);
        fail("expected " + ServiceException.class.getName());
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
