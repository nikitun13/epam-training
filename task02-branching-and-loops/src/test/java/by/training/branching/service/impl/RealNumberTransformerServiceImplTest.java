package by.training.branching.service.impl;

import by.training.branching.service.ServiceException;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.*;

public class RealNumberTransformerServiceImplTest {

    private RealNumberTransformerServiceImpl service;

    @DataProvider(name = "positiveDataForTransformTwoDifferentRealNumbers")
    public Object[][] createPositiveDataForTransformTwoDifferentRealNumbers() {
        return new Object[][]{
                {new double[]{-1d, 1d}, List.of(0d, -2d)},
                {new double[]{1d, -1d}, List.of(-2d, 0d)},
                {new double[]{-4d, -2d}, List.of(-3d, 16d)},
                {new double[]{-2d, -4d}, List.of(16d, -3d)},
                {new double[]{2d, 4d}, List.of(3d, 16d)},
                {new double[]{4d, 2d}, List.of(16d, 3d)},
                {new double[]{0d, 5d}, List.of(2.5, 0d)},
                {new double[]{5d, 0d}, List.of(0d, 2.5)},
                {new double[]{0.75, 0.25}, List.of(0.375, 0.5)},
                {new double[]{0.25, 0.75}, List.of(0.5, 0.375)},
                {new double[]{-0.75, 0.25}, List.of(-0.25, -0.375)},
                {new double[]{0.25, -0.75}, List.of(-0.375, -0.25)},
                {new double[]{-0.25, -0.75}, List.of(-0.5, 0.375)},
                {new double[]{-0.75, -0.25}, List.of(0.375, -0.5)}
        };
    }

    @DataProvider(name = "negativeDataForTransformTwoDifferentRealNumbers")
    public Object[][] createNegativeDataForTransformTwoDifferentRealNumbers() {
        return new Object[][]{
                {new double[]{0d, 0d}, null},
                {new double[]{0.0001, 0.0001}, null},
                {new double[]{1d, 1d}, null},
                {new double[]{-1d, -1d}, null},
                {new double[]{0.5, 0.5}, null},
                {new double[]{-0.5, -0.5}, null},
                {new double[]{-0.13425, -0.13425}, null},
                {new double[]{0.13425, 0.13425}, null}
        };
    }

    @DataProvider(name = "dataForFirstScenarioForTransformThreeRealNumbers")
    public Object[][] createDataForFirstScenarioForTransformThreeRealNumbers() {
        return new Object[][]{
                {new double[]{3d, 2d, 1d}, List.of(6d, 4d, 2d)},
                {new double[]{-1d, -2d, -3d}, List.of(-2d, -4d, -6d)},
                {new double[]{3.5, 2.75, 1.25}, List.of(7d, 5.5, 2.5)},
                {new double[]{-1.25, -2.75, -3.5}, List.of(-2.5, -5.5, -7d)},
                {new double[]{1.25, -2.75, -3.5}, List.of(2.5, -5.5, -7d)},
                {new double[]{2.75, -1.25, -3.5}, List.of(5.5, -2.5, -7d)},
                {new double[]{3.5, -1.25, -2.75}, List.of(7d, -2.5, -5.5)},
                {new double[]{3.5, 1.25, -2.75}, List.of(7d, 2.5, -5.5)},
                {new double[]{0.1, 0.01, 0.001}, List.of(0.2, 0.02, 0.002)}
        };
    }

    @DataProvider(name = "dataForSecondScenarioForTransformThreeRealNumbers")
    public Object[][] createDataForSecondScenarioForTransformThreeRealNumbers() {
        return new Object[][]{
                {new double[]{1d, 2d, 3d}, List.of(1d, 2d, 3d)},
                {new double[]{3d, 2d, 4d}, List.of(3d, 2d, 4d)},
                {new double[]{-3d, -2d, -1d}, List.of(3d, 2d, 1d)},
                {new double[]{-1.25, -3.5, -2.75}, List.of(1.25, 3.5, 2.75)},
                {new double[]{1.25, 3.5, 2.75}, List.of(1.25, 3.5, 2.75)},
                {new double[]{0.01, 0.01, 0.001}, List.of(0.01, 0.01, 0.001)},
                {new double[]{0.01, 0.01, 0.01}, List.of(0.01, 0.01, 0.01)},
                {new double[]{0.1, 0.01, 0.01}, List.of(0.1, 0.01, 0.01)},
                {new double[]{-0.1, -0.01, -0.01}, List.of(0.1, 0.01, 0.01)},
                {new double[]{-1d, -1d, -1d}, List.of(1d, 1d, 1d)},
                {new double[]{0d, -1d, -1d}, List.of(0d, 1d, 1d)},
                {new double[]{-1d, -1d, -2d}, List.of(1d, 1d, 2d)},
                {new double[]{-1d, -2d, -1d}, List.of(1d, 2d, 1d)}
        };
    }

    @BeforeClass
    public void setUp() {
        service = new RealNumberTransformerServiceImpl();
    }

    @Test(description = "test positive scenario for TransformTwoDifferentRealNumbers method",
            dataProvider = "positiveDataForTransformTwoDifferentRealNumbers")
    public void testPositiveScenarioTransformTwoDifferentRealNumbers(double[] input, List<Double> expected) {
        double a = input[0];
        double b = input[1];
        List<Double> actual = service.transformTwoDifferentRealNumbers(a, b);
        assertEquals(actual, expected,
                String.format("Transformation of %f and %f on input must be %f and %f",
                        a, b, expected.get(0), expected.get(1)
                )
        );
    }

    @Test(description = "test negative scenario for TransformTwoDifferentRealNumbers method",
            dataProvider = "negativeDataForTransformTwoDifferentRealNumbers",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioTransformTwoDifferentRealNumbers(double[] input, Object stub) {
        double a = input[0];
        double b = input[1];
        service.transformTwoDifferentRealNumbers(a, b);
        fail(String.format("Must throw %s for %f and %f on input",
                        ServiceException.class.getName(), a, b
                )
        );
    }

    @Test(description = "test first scenario for TransformThreeRealNumbers, when a > b > c",
            dataProvider = "dataForFirstScenarioForTransformThreeRealNumbers")
    public void testFirstScenarioTransformThreeRealNumbers(double[] input, List<Double> expected) {
        double a = input[0];
        double b = input[1];
        double c = input[2];
        List<Double> actual = service.transformThreeRealNumbers(a, b, c);
        assertEquals(actual, expected,
                String.format("Transformation of %f, %f and %f on input must be %f, %f and %f",
                        a, b, c, expected.get(0), expected.get(1), expected.get(2)
                )
        );
    }

    @Test(description = "test second scenario for TransformThreeRealNumbers",
            dataProvider = "dataForSecondScenarioForTransformThreeRealNumbers")
    public void testSecondScenarioTransformThreeRealNumbers(double[] input, List<Double> expected) {
        double a = input[0];
        double b = input[1];
        double c = input[2];
        List<Double> actual = service.transformThreeRealNumbers(a, b, c);
        assertEquals(actual, expected,
                String.format("Transformation of %f, %f and %f on input must be %f, %f and %f",
                        a, b, c, expected.get(0), expected.get(1), expected.get(2)
                )
        );
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}