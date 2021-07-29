package by.training.linear.model;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AverageFinderTest {

    private AverageFinder averageFinder;

    @BeforeClass
    public void setUp() {
        averageFinder = new AverageFinder();
    }

    @DataProvider(name = "dataForAverage")
    public Object[][] createDataForAverage() {
        return new Object[][]{
                {new double[]{0d, 0d}, 0d},
                {new double[]{5d, 0d}, 2.5},
                {new double[]{17d, 13d}, 15d},
                {new double[]{8.432, 4.542}, 6.487},
                {new double[]{-8.432, -4.542}, -6.487},
                {new double[]{-5d, 5d}, 0d},
                {new double[]{-5d, -5d}, -5d},
                {new double[]{5d, 5d}, 5d},
                {new double[]{-12d, -6d}, -9d}
        };
    }

    @Test(description = "different input for average method",
            dataProvider = "dataForAverage")
    public void testAverage(double[] input, double expected) {
        double firstNumber = input[0];
        double secondNumber = input[1];
        double actual = averageFinder.average(firstNumber, secondNumber);
        assertEquals(actual, expected,
                String.format("Average of %f and %f must be %f.",
                        firstNumber, secondNumber, expected)
        );
    }

    @AfterClass
    public void tierDown() {
        averageFinder = null;
    }
}
