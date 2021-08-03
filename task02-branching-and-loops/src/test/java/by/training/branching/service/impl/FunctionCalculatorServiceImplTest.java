package by.training.branching.service.impl;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class FunctionCalculatorServiceImplTest {

    private static final double DELTA = 0.00001;

    private FunctionCalculatorServiceImpl service;

    @DataProvider(name = "firstScenarioDataForCalculate")
    public Object[][] createFirstScenarioDataForCalculate() {
        return new Object[][]{
                {0d, 9d},
                {1d, 8d},
                {13d, -2188d},
                {-1d, 10d},
                {-13d, 2206d},
                {11.43543, -1486.40042}
        };
    }

    @DataProvider(name = "secondScenarioDataForCalculate")
    public Object[][] createSecondScenarioDataForCalculate() {
        return new Object[][]{
                {14d, -0.2},
                {19d, -0.15},
                {22d, -0.13043},
                {13.00001, -0.21428},
                {27.54358, -0.10510}
        };
    }

    @BeforeClass
    public void setUp() {
        service = new FunctionCalculatorServiceImpl();
    }

    @Test(description = "test first scenario of calculate method, when x <= 13",
            dataProvider = "firstScenarioDataForCalculate")
    public void testFirstScenarioCalculate(double x, double expected) {
        double actual = service.calculate(x);
        assertEquals(actual, expected, DELTA,
                String.format("calculation result for %f must be %f", x, expected)
        );
    }

    @Test(description = "test second scenario of calculate method, when x > 13",
            dataProvider = "secondScenarioDataForCalculate")
    public void testSecondScenarioCalculate(double x, double expected) {
        double actual = service.calculate(x);
        assertEquals(actual, expected, DELTA,
                String.format("calculation result for %f must be %f", x, expected)
        );
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
