package by.training.linear.model;

import by.training.linear.entity.Resistor;
import by.training.linear.exception.InvalidResistorException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ResistanceCalculatorTest {

    private ResistanceCalculator calculator;

    private static final double DELTA = 0.00001;

    @BeforeClass
    public void setUp() {
        calculator = new ResistanceCalculator();
    }

    @DataProvider(name = "positiveDataForCalculateResistanceOfParallelResistors")
    public Object[][] createPositiveDataForCalculateResistanceOfParallelResistors() {
        return new Object[][]{
                {new double[]{1d, 2d, 3d}, 0.54545},
                {new double[]{1d, 3d, 2d}, 0.54545},
                {new double[]{2d, 1d, 3d}, 0.54545},
                {new double[]{2d, 3d, 1d}, 0.54545},
                {new double[]{3d, 1d, 2d}, 0.54545},
                {new double[]{3d, 2d, 1d}, 0.54545},
                {new double[]{1d, 1d, 1d}, 0.33333},
                {new double[]{2.5, 2.5, 2.5}, 0.83333},
                {new double[]{1.43, 2.65, 0.87}, 0.44921},
                {new double[]{2.65, 1.43, 0.87}, 0.44921},
                {new double[]{0.87, 1.43, 2.65}, 0.44921},
                {new double[]{20d, 10d, 20d}, 5d},
                {new double[]{10d, 20d, 20d}, 5d},
                {new double[]{20d, 20d, 10d}, 5d},
                {new double[]{}, 0d},
                {new double[]{3d, 2d}, 1.2},
                {new double[]{2d, 3d}, 1.2},
                {new double[]{5d}, 5d},
                {new double[]{3d, 5d, 7d, 2d}, 0.8502},
        };
    }

    @DataProvider(name = "negativeDataForCalculateResistanceOfParallelResistors")
    public static Object[][] negativeDataForCalculateResistanceOfParallelResistors() {
        return new Object[][]{
                {new double[]{0d, 2d, 3d}, null},
                {new double[]{1d, 0d, 3d}, null},
                {new double[]{1d, 2d, 0d}, null},
                {new double[]{-1d, 2d, 3d}, null},
                {new double[]{1d, -2d, 3d}, null},
                {new double[]{1d, 2d, -3d}, null},
                {new double[]{0d, 0d, 0d}, null},
                {new double[]{-1d, -2d, -3d}, null},
                {new double[]{-1.434, -2.432, -3.242}, null},
                {new double[]{0d}, null},
                {new double[]{0d, 0d, 0d, 0d}, null},
                {new double[]{-1.434, -2.432}, null},
                {new double[]{-1.434, -2.432, 0d, -0.324}, null},
                {new double[]{-1.434, -2.432, 0.5, -0.324}, null}
        };
    }

    @DataProvider(name = "nullDataForCalculateResistanceOfParallelResistors")
    public static Object[][] nullDataForCalculateResistanceOfParallelResistors() {
        return new Object[][]{
                {new Resistor[]{null, new Resistor(2d), new Resistor(3d)}, null},
                {new Resistor[]{new Resistor(1d), null, new Resistor(3d)}, null},
                {new Resistor[]{new Resistor(1d), new Resistor(2d), null}, null},
                {new Resistor[]{null}, null},
                {new Resistor[]{null, null, null}, null},
                {new Resistor[]{null, null, null, null}, null}
        };
    }

    @Test(description = "test positive scenario for calculateResistanceOfParallelResistors method",
            dataProvider = "positiveDataForCalculateResistanceOfParallelResistors")
    public void testPositiveScenarioCalculateResistanceOfParallelResistors(double[] resistance, double expected) {
        List<Resistor> resistors = Arrays.stream(resistance)
                .mapToObj(Resistor::new)
                .collect(toList());
        double actual = calculator.calculateResistanceOfParallelResistors(resistors);
        assertEquals(actual, expected, DELTA,
                "resistance of " + resistors + " must be " + expected);
    }

    @Test(description = "test negative scenario for calculateResistanceOfParallelResistors method",
            dataProvider = "negativeDataForCalculateResistanceOfParallelResistors",
            expectedExceptions = InvalidResistorException.class)
    public void testNegativeScenarioCalculateResistanceOfParallelResistors(double[] resistance, Object stub) {
        List<Resistor> resistors = Arrays.stream(resistance)
                .mapToObj(Resistor::new)
                .collect(toList());
        calculator.calculateResistanceOfParallelResistors(resistors);
        fail("Must throw " + InvalidResistorException.class.getName() + " for " + resistors + " on input");
    }

    @Test(description = "test null list scenario for calculateResistanceOfParallelResistors method",
            expectedExceptions = NullPointerException.class)
    public void testNullListScenarioCalculateResistanceOfParallelResistors() {
        calculator.calculateResistanceOfParallelResistors(null);
        fail("Must throw " + NullPointerException.class.getName() + " for null on input");
    }

    @Test(description = "test null inside list scenario for calculateResistanceOfParallelResistors method",
            dataProvider = "nullDataForCalculateResistanceOfParallelResistors",
            expectedExceptions = NullPointerException.class)
    public void testNullInsideListScenarioCalculateResistanceOfParallelResistors(Resistor[] resistorArray, Object stub) {
        List<Resistor> resistors = Arrays.asList(resistorArray);
        calculator.calculateResistanceOfParallelResistors(resistors);
        fail("Must throw " + NullPointerException.class.getName() + " for null inside list");
    }

    @AfterClass
    public void tierDown() {
        calculator = null;
    }
}
