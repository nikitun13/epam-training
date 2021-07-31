package by.learning.branching.service.impl;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IntegerComparatorServiceImplTest {

    private IntegerComparatorServiceImpl service;

    @DataProvider(name = "dataForMin")
    public Object[][] createDataForMin() {
        return new Object[][]{
                {new int[]{0, 0}, 0},
                {new int[]{2, 2}, 2},
                {new int[]{1, 3}, 1},
                {new int[]{3, 1}, 1},
                {new int[]{-1, 0}, -1},
                {new int[]{0, -1}, -1},
                {new int[]{-3, -1}, -3},
                {new int[]{-1, -3}, -3},
                {new int[]{-3, 3}, -3},
                {new int[]{3, -3}, -3},
                {new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}, Integer.MIN_VALUE},
                {new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, Integer.MIN_VALUE}
        };
    }

    @BeforeClass
    public void setUp() {
        service = new IntegerComparatorServiceImpl();
    }

    @Test(description = "different input for min method",
            dataProvider = "dataForMin")
    public void testMin(int[] input, int expected) {
        int a = input[0];
        int b = input[1];
        int actual = service.min(a, b);
        assertEquals(actual, expected,
                String.format("minimum of %d and %d must be %d", a, b, expected)
        );
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}