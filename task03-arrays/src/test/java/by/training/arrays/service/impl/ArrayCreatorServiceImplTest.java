package by.training.arrays.service.impl;

import by.training.arrays.entity.Array;
import by.training.arrays.service.ServiceException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.testng.Assert.*;

public class ArrayCreatorServiceImplTest {

    private ArrayCreatorServiceImpl service;

    @DataProvider(name = "positiveDataForFillRandomized")
    public static Object[][] createPositiveDataForFillRandomized() {
        return new Object[][]{
                {1, -500, 1000},
                {1, 0, 0},
                {1, 100, 100},
                {1, 99, 100},
                {2, -500, 1000},
                {2, -5, 5},
                {5, -5, -3},
                {5, 0, 0},
                {5, 1, 1},
                {5, -1, -1},
                {10, 3, 5},
                {10, 0, 1},
                {10, -1, 0},
                {10, -1, 1},
                {7, -7, 0},
                {7, 1, 7},
                {0, -1000, 1000},
                {0, 0, 0},
                {0, 1, 1},
                {0, -1, 1},
                {0, 3, 5},
                {0, -5, -3}
        };
    }

    @DataProvider(name = "negativeDataForFillRandomized")
    public static Object[][] createNegativeDataForFillRandomized() {
        return new Object[][]{
                {-1, -500, 1000},
                {-4, -5, 5},
                {-3, -5, -3},
                {-100, 0, 0},
                {-3, 1, -1},
                {0, 1, -1},
                {10, 3, -5},
                {-10, 3, -5},
                {10, 0, -1},
                {10, 1, -4},
                {10, -1, -5},
                {7, 7, 0},
                {7, 1, -7}
        };
    }

    @DataProvider(name = "positiveDataForCreateFromFile")
    public static Iterator<Object[]> createPositiveDataForCreateFromFile() throws IOException {
        Path parent = Path.of("src", "test", "resources", "arrayCreator", "createFromFile", "positive");
        DirectoryStream<Path> children = Files.newDirectoryStream(parent);
        List<Array<String>> list = List.of(
                new Array<>(new String[]{"1"}),
                new Array<>(new String[]{"1", "2"}),
                new Array<>(new String[]{"1", "2", "3"}),
                new Array<>(new String[]{"qwer", "qwer", "qwer"}),
                new Array<>(new String[]{"1", "1", "1", "1"}),
                new Array<>(new String[]{"q", "q", "q", "q"}),
                new Array<>(new String[]{"a1", "b2", "c3", "d4"})
        );
        return StreamSupport.stream(children.spliterator(), false)
                .map(path -> new Object[]{path, list})
                .iterator();
    }

    @DataProvider(name = "positiveDataForConvertToIntegerArray")
    public static Object[][] createPositiveDataForConvertToIntegerArray() {
        return new Object[][]{
                {new Array<>(new String[]{"1", "2", "3"}), new Array<>(new Integer[]{1, 2, 3})},
                {new Array<>(new String[]{"-1", "-2", "-3"}), new Array<>(new Integer[]{-1, -2, -3})},
                {new Array<>(new String[0]), new Array<>(new Integer[0])},
                {new Array<>(new String[]{"0"}), new Array<>(new Integer[]{0})},
                {new Array<>(new String[]{"-1", "-2", "-3", "0", "1", "2", "3"}), new Array<>(new Integer[]{-1, -2, -3, 0, 1, 2, 3})}
        };
    }

    @DataProvider(name = "negativeDataForConvertToIntegerArray")
    public static Object[][] createNegativeDataForConvertToIntegerArray() {
        return new Object[][]{
                {new Array<>(new String[]{"1", "2", "3", "d"})},
                {new Array<>(new String[]{"1", "2", "d"})},
                {new Array<>(new String[]{"1", "d", "3"})},
                {new Array<>(new String[]{"d", "2", "3"})},
                {new Array<>(new String[]{"1d", "2", "3"})},
                {new Array<>(new String[]{"1d", "2f", "3"})},
                {new Array<>(new String[]{"1", "2f", "3"})},
                {new Array<>(new String[]{"1.0", "2", "3"})},
                {new Array<>(new String[]{"1", "2.0", "3"})},
                {new Array<>(new String[]{"1", "2", "3.0"})},
                {new Array<>(new String[]{"1", "2", "3.4"})},
                {new Array<>(new String[]{"1", "2.3", "3"})},
                {new Array<>(new String[]{"1.7", "2.3", "3.1"})},
                {new Array<>(new String[]{""})},
                {new Array<>(new String[]{"d"})},
                {new Array<>(new String[]{"4f"})},
                {new Array<>(new String[]{"4.4f"})},
                {new Array<>(new String[]{"d4"})},
                {new Array<>(new String[]{"adf"})},
                {new Array<>(new String[]{"adf", "esf", "fes"})}
        };
    }

    @BeforeClass
    public void setUp() {
        service = new ArrayCreatorServiceImpl();
    }

    @Test(description = "test positive scenario for fillRandomized method",
            dataProvider = "positiveDataForFillRandomized")
    public void testPositiveScenarioFillRandomized(int size, int minValue, int maxValue) {
        Array<Integer> actual = service.fillRandomized(size, minValue, maxValue);
        assertNotNull(actual, "must create Array with random values");
    }

    @Test(description = "test negative scenario for fillRandomized method",
            dataProvider = "negativeDataForFillRandomized",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioFillRandomized(int size, int minValue, int maxValue) {
        service.fillRandomized(size, minValue, maxValue);
        fail("must throw " + ServiceException.class.getName() + " for invalid data on input");
    }

    @Test(description = "test positive data for createFromFile method",
            dataProvider = "positiveDataForCreateFromFile")
    public void testPositiveScenarioCreateFromFile(Path path, List<Array<String>> expected) {
        List<Array<String>> actual = service.createFromFile(path);
        assertEquals(actual, expected, "must create list: " + expected);
    }

    @Test(description = "test negative data for createFromFile method",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioCreateFromFile() {
        Path path = Path.of("path", "to", "invalid", "file.txt");
        service.createFromFile(path);
        fail("must throw " + ServiceException.class.getName() + " for invalid input");
    }

    @Test(description = "test path is null for createFromFile method",
            expectedExceptions = NullPointerException.class)
    public void testPathIsNullScenarioCreateFromFile() {
        service.createFromFile(null);
        fail("must throw " + NullPointerException.class.getName() + " for path in null on input");
    }

    @Test(description = "test positive scenario for convertToIntegerArray method",
            dataProvider = "positiveDataForConvertToIntegerArray")
    public void testPositiveScenarioConvertToIntegerArray(Array<String> array, Array<Integer> expected) {
        Array<Integer> actual = service.convertToIntegerArray(array);
        assertEquals(actual, expected, "must convert to: " + expected);
    }

    @Test(description = "test negative scenario for convertToIntegerArray method",
            dataProvider = "negativeDataForConvertToIntegerArray",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioConvertToIntegerArray(Array<String> array) {
        service.convertToIntegerArray(array);
        fail("must throw " + ServiceException.class.getName()
                + " for incompatible array " + array
        );
    }

    @Test(description = "test array is null scenario for convertToIntegerArray method",
            expectedExceptions = NullPointerException.class)
    public void testArrayIsNullScenarioConvertToIntegerArray() {
        service.convertToIntegerArray(null);
        fail("must throw " + NullPointerException.class.getName()
                + " for array is null on input"
        );
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
