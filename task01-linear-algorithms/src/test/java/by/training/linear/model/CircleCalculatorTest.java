package by.training.linear.model;

import by.training.linear.entity.Circle;
import by.training.linear.exception.InvalidCircleException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class CircleCalculatorTest {

    private CircleCalculator calculator;

    private static final double DELTA = 0.00001;

    @BeforeClass
    public void setUp() {
        calculator = new CircleCalculator();
    }

    @DataProvider(name = "positiveDataForCalculateCircleArea")
    public Object[][] createPositiveDataForCalculateCircleArea() {
        return
                new Object[][]{
                        {1d, 0.07957},
                        {4d, 1.27324},
                        {50d, 198.94368},
                        {0.145, 0.00167},
                        {2.35, 0.43946},
                };
    }

    @DataProvider(name = "negativeDataForCalculateCircleArea")
    public Object[][] createNegativeDataForCalculateCircleArea() {
        return
                new Object[][]{
                        {0d, null},
                        {-1d, null},
                        {-1.4d, null},
                        {-5d, null}
                };
    }

    @Test(description = "test positive scenario for calculateCircleArea method",
            dataProvider = "positiveDataForCalculateCircleArea")
    public void testPositiveScenarioCalculateCircleArea(double circumference, double expected) {
        Circle circle = new Circle(circumference);
        double actual = calculator.calculateCircleArea(circle);
        assertEquals(actual, expected, DELTA,
                String.format("Area of a circle bounded by circumference %f must be %f", circumference, expected)
        );
    }

    @Test(description = "test negative scenario for calculateCircleArea method",
            dataProvider = "negativeDataForCalculateCircleArea",
            expectedExceptions = InvalidCircleException.class)
    public void testNegativeScenarioCalculateCircleArea(double circumference, Object stub) {
        Circle circle = new Circle(circumference);
        calculator.calculateCircleArea(circle);
        fail("Must throw " + InvalidCircleException.class.getName() + " for " + circle + " on input");
    }

    @Test(description = "test null scenario for calculateCircleArea method",
            expectedExceptions = NullPointerException.class)
    public void testNullScenarioCalculateCircleArea() {
        calculator.calculateCircleArea(null);
        fail("Must throw " + NullPointerException.class.getName() + " for null on input");
    }

    @AfterClass
    public void tierDown() {
        calculator = null;
    }
}
