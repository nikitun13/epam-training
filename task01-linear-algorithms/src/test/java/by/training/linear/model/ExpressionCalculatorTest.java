package by.training.linear.model;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ExpressionCalculatorTest {

    private ExpressionCalculator calculator;

    @BeforeClass
    public void setUp() {
        calculator = new ExpressionCalculator();
    }

    @DataProvider(name = "dataForCalculateFirstExpression")
    public Object[][] createDataForCalculateFirstExpression() {
        return new Object[][]{
                {0L, 0L},
                {1L, -3L},
                {-1L, 9L},
                {3L, -87L},
                {-3L, 141L}
        };
    }

    @DataProvider(name = "dataForCalculateSecondExpression")
    public Object[][] createDataForCalculateSecondExpression() {
        return new Object[][]{
                {0L, 1L},
                {1L, 10L},
                {-1L, -2L},
                {3L, 142L},
                {-3L, -86L}
        };
    }

    @Test(description = "different input for calculateFirstExpression method",
            dataProvider = "dataForCalculateFirstExpression")
    public void testCalculateFirstExpression(long x, long expected) {
        long actual = calculator.calculateFirstExpression(x);
        assertEquals(actual, expected,
                "Must be " + expected + " for x = " + x
        );
    }

    @Test(description = "different input for calculateSecondExpression method",
            dataProvider = "dataForCalculateSecondExpression")
    public void testCalculateSecondExpression(long x, long expected) {
        long actual = calculator.calculateSecondExpression(x);
        assertEquals(actual, expected,
                "Must be " + expected + " for x = " + x
        );
    }

    @AfterClass
    public void tierDown() {
        calculator = null;
    }
}
