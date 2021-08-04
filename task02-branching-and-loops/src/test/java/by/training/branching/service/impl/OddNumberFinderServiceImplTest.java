package by.training.branching.service.impl;

import by.training.branching.service.ServiceException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class OddNumberFinderServiceImplTest {

    private OddNumberFinderServiceImpl service;

    @DataProvider(name = "positiveDataForFindSumOfOddNumbersInRange")
    public static Object[][] createPositiveDataForFindSumOfOddNumbersInRange() {
        return new Object[][]{
                {new int[]{0, 1}, 1L},
                {new int[]{-1, 1}, 0L},
                {new int[]{-1, 2}, 0L},
                {new int[]{-1, 0}, -1L},
                {new int[]{-5, -1}, -9L},
                {new int[]{1, 5}, 9L},
                {new int[]{-5, 5}, 0L},
                {new int[]{1, 30}, 225L},
                {new int[]{0, 0}, 0L},
                {new int[]{2, 2}, 0L},
                {new int[]{-2, -2}, 0L},
                {new int[]{5, 5}, 5L},
                {new int[]{-5, -5}, -5L}
        };
    }

    @DataProvider(name = "negativeDataForFindSumOfOddNumbersInRange")
    public static Object[][] createNegativeDataForFindSumOfOddNumbersInRange() {
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

    @Test(description = "test positive scenario for findSumOfOddNumbersInRange method",
            dataProvider = "positiveDataForFindSumOfOddNumbersInRange")
    public void testPositiveScenarioFindSumOfOddNumbersInRange(int[] range, long expected) {
        int lowerBound = range[0];
        int upperBound = range[1];
        long actual = service.findSumOfOddNumbersInRange(lowerBound, upperBound);
        assertEquals(actual, expected,
                String.format("sum must be %d for lower bound %d and upper bound %d",
                        expected, lowerBound, upperBound
                )
        );
    }

    @Test(description = "test negative scenario for findSumOfOddNumbersInRange method",
            dataProvider = "negativeDataForFindSumOfOddNumbersInRange",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioFindSumOfOddNumbersInRange(int lowerBound, int upperBound) {
        service.findSumOfOddNumbersInRange(lowerBound, upperBound);
        fail(String.format("must throw %s for lower bound %d and upper bound %d",
                ServiceException.class.getName(), lowerBound, upperBound
        ));
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
