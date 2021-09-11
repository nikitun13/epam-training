package by.training.multithreading.service.impl;

import by.training.multithreading.entity.Matrix;
import by.training.multithreading.service.MatrixCreatorService;
import by.training.multithreading.service.ServiceException;
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

public class MatrixCreatorServiceImplTest {

    private MatrixCreatorService service;

    @DataProvider(name = "positiveDataForCreateFromFile")
    public static Iterator<Object[]> createPositiveDataForCreateFromFile() throws IOException {
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
                .map(path -> new Object[]{path.toString(), queue.remove()})
                .iterator();
    }

    @DataProvider(name = "negativeDataInTheFileForCreateFromFile")
    public static Iterator<Object[]> createNegativeDataForCreateFromFile() throws IOException {
        Path parentDir = Path.of("src/test/resources/matrix/negative");
        DirectoryStream<Path> children = Files.newDirectoryStream(parentDir);
        return StreamSupport.stream(children.spliterator(), false)
                .map(path -> new Object[]{path.toString()})
                .iterator();
    }

    @DataProvider(name = "negativeInputDataForCreateFromFile")
    public static Object[][] createNegativeInputDataForCreateFromFile() {
        return new Object[][]{
                {null},
                {"invalid/path/to/data.txt"},
                {"dsfsdfssdf"},
                {"dsfsdfsdfdssdf54234"},
                {"234dsfsdfssdf"},
                {"d2sf4sd4f22ss423df"},
                {"242423432"}
        };
    }

    @BeforeClass
    public void setUp() {
        service = new MatrixCreatorServiceImpl();
    }

    @Test(description = "test positive data for CreateFromFile method",
            dataProvider = "positiveDataForCreateFromFile")
    public void testPositiveDataCreateFromFile(String pathToFile, Matrix expected) {
        try {
            Matrix actual = service.createFromFile(pathToFile);
            assertEquals(actual, expected, "invalid matrix");
        } catch (ServiceException e) {
            fail("service exception occurred", e);
        }
    }

    @Test(description = "test negative data in the file for createFromFile method",
            dataProvider = "negativeDataInTheFileForCreateFromFile",
            expectedExceptions = ServiceException.class)
    public void testNegativeDataInTheFileCreateFromFile(String pathToFile) throws ServiceException {
        service.createFromFile(pathToFile);
        fail("expected " + ServiceException.class.getName());
    }

    @Test(description = "test negative input data for createFromFile method",
            dataProvider = "negativeInputDataForCreateFromFile",
            expectedExceptions = ServiceException.class)
    public void testNegativeInputDataCreateFromFile(String pathToFile) throws ServiceException {
        service.createFromFile(pathToFile);
        fail("expected " + ServiceException.class.getName());
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
