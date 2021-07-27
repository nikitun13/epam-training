package by.training.linear.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PowerCalculator {

    private static final Logger logger = LogManager.getLogger(PowerCalculator.class);

    /**
     * 15. Write program, which prints first four powers of π.
     */
    public double powPi(double exponent) {
        logger.debug("exponent = {}", exponent);
        double result = Math.pow(Math.PI, exponent);
        logger.debug("result = {}", result);
        return result;
    }
}
