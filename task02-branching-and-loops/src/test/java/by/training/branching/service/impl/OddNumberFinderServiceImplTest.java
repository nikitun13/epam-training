package by.training.branching.service.impl;

import by.training.branching.service.ServiceException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.Collections;
import java.util.List;

public class OddNumberFinderServiceImplTest {

    private OddNumberFinderServiceImpl service;

    @DataProvider(name = "positiveDataForFindOddNumbersInRange")
    public static Object[][] createPositiveDataForFindOddNumbersInRange() {
        return new Object[][]{
                {new int[]{0, 1}, List.of(1)},
                {new int[]{-1, 1}, List.of(-1, 1)},
                {new int[]{-1, 2}, List.of(-1, 1)},
                {new int[]{-1, 0}, List.of(-1)},
                {new int[]{-5, -1}, List.of(-5, -3, -1)},
                {new int[]{1, 5}, List.of(1, 3, 5)},
                {new int[]{-5, 5}, List.of(-5, -3, -1, 1, 3, 5)},
                {new int[]{1, 30}, List.of(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29)},
                {new int[]{0, 0}, Collections.emptyList()},
                {new int[]{2, 2}, Collections.emptyList()},
                {new int[]{-2, -2}, Collections.emptyList()},
                {new int[]{5, 5}, List.of(5)},
                {new int[]{-5, -5}, List.of(-5)}
        };
    }

    @DataProvider(name = "negativeDataForFindOddNumbersInRange")
    public static Object[][] createNegativeDataForFindOddNumbersInRange() {
        return new Object[][]{
                {3, 2},
                {1, -1},
                {0, -1},
                {5, -5},
                {1, 0},
                {-2, -4}
        };
    }

    @BeforeClass
    public void setUp() {
        service = new OddNumberFinderServiceImpl();
    }

    @Test(description = "test positive scenario for findOddNumbersInRange method",
            dataProvider = "positiveDataForFindOddNumbersInRange")
    public void testPositiveScenarioFindOddNumbersInRange(int[] range, List<Integer> expected) {
        int lowerBound = range[0];
        int upperBound = range[1];
        List<Integer> actual = service.findOddNumbersInRange(lowerBound, upperBound);
        assertEquals(actual, expected,
                String.format("Must be %s for lower bound %d and upper bound %d",
                        expected, lowerBound, upperBound
                )
        );
    }

    @Test(description = "test negative scenario for findOddNumbersInRange method",
            dataProvider = "negativeDataForFindOddNumbersInRange",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioFindOddNumbersInRange(int lowerBound, int upperBound) {
        service.findOddNumbersInRange(lowerBound, upperBound);
        fail(String.format("Must throw %s for lower bound %d and upper bound %d",
                ServiceException.class.getName(), lowerBound, upperBound
        ));
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
