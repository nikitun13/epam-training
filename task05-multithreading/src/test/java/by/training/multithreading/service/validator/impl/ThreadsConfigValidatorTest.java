package by.training.multithreading.service.validator.impl;

import by.training.multithreading.service.validator.Validator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ThreadsConfigValidatorTest {

    private final Validator<List<Integer>> validator = ThreadsConfigValidator.getInstance();

    @DataProvider(name = "validDataForIsValid")
    public static Object[][] createValidDataForIsValid() {
        return new Object[][]{
                {List.of(1, 2)},
                {List.of(1, 2, 3)},
                {List.of(1, 2, 3, 4)},
                {List.of(1, 2, 3, 4, 5)},
                {List.of(1, 2, 3, 4, 5, 6)},
                {List.of(19, 12, 30, 42, 55, 64)}
        };
    }

    @DataProvider(name = "invalidDataForIsValid")
    public static Object[][] createInvalidDataForIsValid() {
        return new Object[][]{
                {List.of(1)},
                {List.of(0)},
                {List.of(0, 1)},
                {List.of(-1)},
                {List.of(-1, -2, -3)},
                {List.of(1, -2, 3, 4)},
                {List.of(1, 2, 3, -4, 5)},
                {List.of(1, 2, 3, 4, 5, 6, 7)},
                {List.of(19, 12, 30, 42, 55, 64, 100)},
                {List.of(19, 12, 30, 42, 55, 64, 100, 43, 54)}
        };
    }

    @Test(description = "test valid data for isValid method",
            dataProvider = "validDataForIsValid")
    public void testValidDataForIsValid(List<Integer> config) {
        boolean actual = validator.isValid(config);
        assertTrue(actual);
    }

    @Test(description = "test invalid data for isValid method",
            dataProvider = "invalidDataForIsValid")
    public void testInvalidDataForIsValid(List<Integer> config) {
        boolean actual = validator.isValid(config);
        assertFalse(actual);
    }
}
