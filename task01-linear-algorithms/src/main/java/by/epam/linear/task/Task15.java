package by.epam.linear.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * Write program, which prints first four powers of π.
 */
public final class Task15 {

    private static Logger log = LogManager.getLogger(Task15.class.getName());

    private Task15() {
    }

    public static double powPi(double exponent) {
        return Math.pow(Math.PI, exponent);
    }

    public static void printFirstFourPowersOfPi() {

        double first = powPi(1);
        double second = powPi(2);
        double third = powPi(3);
        double fourth = powPi(4);

        log.info("π^1: {}", first);
        log.info("π^2: {}", second);
        log.info("π^3: {}", third);
        log.info("π^4: {}", fourth);
    }
}