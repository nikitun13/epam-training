package by.training.arrays.service;

import by.training.arrays.entity.Array;
import by.training.arrays.service.impl.*;
import org.testng.annotations.*;
import org.testng.internal.collections.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.testng.Assert.*;


public class ArraySortingServiceTest {

    private ArraySortingService service;

    public ArraySortingServiceTest() {
    }

    @Factory(dataProvider = "serviceImplementationData")
    public ArraySortingServiceTest(ArraySortingService service) {
        this.service = service;
    }

    @DataProvider(name = "serviceImplementationData")
    public Object[][] serviceImplementationData() {
        return new Object[][]{
                {new SelectionSort()},
                {new InsertionSort()},
                {new BubbleSort()},
                {new ShakerSort()},
                {new ShellSort()}
        };
    }

    @DataProvider(name = "integerDataForSort")
    public static Iterator<Object[]> createIntegerDataForSort() throws IOException {
        Path path = Path.of("src", "test", "resources", "sort", "sort-integers.txt");
        return Files.lines(path)
                .map(line -> line.split("\\s+"))
                .map(array -> Arrays.stream(array)
                        .map(Integer::valueOf)
                        .toArray(Integer[]::new)
                )
                .map(array -> Pair.of(new Array<>(array), array))
                .peek(pair -> Arrays.sort(pair.second()))
                .map(pair -> new Object[]{pair.first(), new Array<>(pair.second())})
                .iterator();
    }

    @DataProvider(name = "stringDataForSort")
    public static Iterator<Object[]> createStringDataForSort() throws IOException {
        Path path = Path.of("src", "test", "resources", "sort", "sort-strings.txt");
        return Files.lines(path)
                .map(line -> line.split("\\s+"))
                .map(array -> Pair.of(new Array<>(array), array))
                .peek(pair -> Arrays.sort(pair.second()))
                .map(pair -> new Object[]{pair.first(), new Array<>(pair.second())})
                .iterator();
    }

    @Test(description = "test Integers for sort method",
            dataProvider = "integerDataForSort")
    public <T extends Comparable<? super T>> void testIntegerDataForSort(Array<T> array, Array<T> expected) {
        service.sort(array);
        assertEquals(array, expected);
    }

    @Test(description = "test Strings for sort method",
            dataProvider = "stringDataForSort")
    public <T extends Comparable<? super T>> void testStringDataForSort(Array<T> array, Array<T> expected) {
        service.sort(array);
        assertEquals(array, expected);
    }

    @Test(description = "test array is null scenario for sort method",
            expectedExceptions = NullPointerException.class)
    public void testArrayIsNullScenarioForSort() {
        service.sort(null);
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
