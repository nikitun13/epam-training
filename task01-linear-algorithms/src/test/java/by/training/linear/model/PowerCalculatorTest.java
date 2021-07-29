package by.training.linear.model;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PowerCalculatorTest {

    private PowerCalculator calculator;

    private static final double DELTA = 0.00001;

    @BeforeClass
    public void setUp() {
        calculator = new PowerCalculator();
    }

    @DataProvider(name = "dataForPowPi")
    public Object[][] createDataForPowPi() {
        return
                new Object[][]{
                        {0d, 1d},
                        {1d, Math.PI},
                        {2d, 9.86960},
                        {3d, 31.00628},
                        {4d, 97.40909},
                        {Math.PI, 36.46216},
                        {0.786, 2.45901},
                        {-1d, 0.31831},
                        {-2.36, 0.0671},
                        {-Math.PI, 0.02742}
                };
    }

    @Test(description = "different input for powPi method",
            dataProvider = "dataForPowPi")
    public void testPowPi(double exponent, double expected) {
        double actual = calculator.powPi(exponent);
        assertEquals(actual, expected, DELTA,
                String.format("π^%f must be %f", exponent, expected)
        );
    }

    @AfterClass
    public void tierDown() {
        calculator = null;
    }
}
