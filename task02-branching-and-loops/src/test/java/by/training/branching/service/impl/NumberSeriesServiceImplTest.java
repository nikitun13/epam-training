package by.training.branching.service.impl;

import by.training.branching.service.ServiceException;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class NumberSeriesServiceImplTest {

    private NumberSeriesServiceImpl service;

    private static final double DELTA = 0.00001;

    @DataProvider(name = "positiveDataForCalculateSumOfPowersOfTwo")
    public static Object[][] createPositiveDataForCalculateSumOfPowersOfTwo() {
        return new Object[][]{
                {0, 1L},
                {1, 3L},
                {2, 7L},
                {3, 15L},
                {4, 31L},
                {5, 63L},
                {6, 127L},
                {7, 255L},
                {8, 511L},
                {9, 1023L},
                {10, 2047L},
                {11, 4095L},
                {15, 65535L},
                {25, 67108863L},
                {40, 2199023255551L}
        };
    }

    @DataProvider(name = "negativeDataForCalculateSumOfPowersOfTwo")
    public static Object[][] createNegativeDataForCalculateSumOfPowersOfTwo() {
        return new Object[][]{
                {-1},
                {-2},
                {-15},
                {-20},
                {-10},
                {-100},
                {Integer.MIN_VALUE}
        };
    }

    @DataProvider(name = "positiveDataForCalculateNumberSeries")
    public static Object[][] createPositiveDataForCalculateNumberSeries() {
        return new Object[][]{
                {0.25, 0.25},
                {0.0357, 0.28571},
                {0.01428, 0.3},
                {0.007692, 0.307692},
                {0.0048076, 0.3125},
                {0.0032894, 0.31578},
                {0.0023923, 0.31818},
                {0.00181818, 0.32},
                {0.000180245, 0.32894},
                {0.00001114852, 0.33222},
                {100d, 0d},
                {15d, 0d},
                {1d, 0d},
                {1.24354, 0d},
                {0.2543, 0d},
                {0.26, 0d},
                {0.3, 0d},
                {0.5, 0d},
                {13.4323, 0d}
        };
    }

    @DataProvider(name = "negativeDataForCalculateNumberSeries")
    public static Object[][] createNegativeDataForCalculateNumberSeries() {
        return new Object[][]{
                {0d},
                {-1d},
                {-1.4},
                {-100d},
                {-0.5},
                {-13.54},
                {-0.0001}
        };
    }

    @BeforeClass
    public void setUp() {
        service = new NumberSeriesServiceImpl();
    }

    @Test(description = "test positive scenario for calculatePowersOfTwo method",
            dataProvider = "positiveDataForCalculateSumOfPowersOfTwo")
    public void testPositiveScenarioCalculateSumOfPowersOfTwo(int lastPower, long expected) {
        long actual = service.calculateSumOfPowersOfTwo(lastPower);
        assertEquals(actual, expected,
                String.format("sum must be %d for last power %d", expected, lastPower)
        );
    }

    @Test(description = "test negative scenario for calculatePowersOfTwo method",
            dataProvider = "negativeDataForCalculateSumOfPowersOfTwo",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioCalculateSumOfPowersOfTwo(int lastPower) {
        service.calculateSumOfPowersOfTwo(lastPower);
        fail(String.format("must throw %s for last power %d on input",
                        ServiceException.class.getName(), lastPower
                )
        );
    }

    @Test(description = "test positive scenario for calculateNumberSeries method",
            dataProvider = "positiveDataForCalculateNumberSeries")
    public void testPositiveScenarioCalculateNumberSeries(double e, double expected) {
        double actual = service.calculateNumberSeries(e);
        assertEquals(actual, expected, DELTA,
                String.format("sum must be %f for e = %f on input", expected, e)
        );
    }

    @Test(description = "test negative scenario for calculateNumberSeries method",
            dataProvider = "negativeDataForCalculateNumberSeries",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioCalculateNumberSeries(double e) {
        service.calculateNumberSeries(e);
        fail(String.format("must throw %s for e = %f on input",
                        ServiceException.class.getName(), e
                )
        );
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
