package by.training.linear.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AverageFinder {

    private static final Logger logger = LogManager.getLogger(AverageFinder.class);

    /**
     * 5. Create an algorithm for finding the average of two numbers
     */
    public double average(double firstNumber, double secondNumber) {
        logger.debug("first number = {}, second number = {}", firstNumber, secondNumber);
        double result = (firstNumber + secondNumber) / 2;
        logger.debug("result = {}", result);
        return result;
    }
}
