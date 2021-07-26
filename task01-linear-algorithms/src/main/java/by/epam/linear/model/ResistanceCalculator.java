package by.epam.linear.model;

import by.epam.linear.entity.Resistor;
import by.epam.linear.exception.InvalidResistorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class ResistanceCalculator {

    private static final Logger logger = LogManager.getLogger(ResistanceCalculator.class);

    /*
     * Three resistors R1, R2, and R3 are connected in parallel.
     * Find the connection resistance.
     */
    public double calculateResistanceOfParallelResistors(List<Resistor> resistors) {
        logger.debug("received: {}", resistors);
        Objects.requireNonNull(resistors, "list of resistors can't be null");
        if (resistors.isEmpty()) {
            return 0d; //returns 0 if there are no resistors
        }
        for (Resistor resistor : resistors) {
            Objects.requireNonNull(resistor);
        }

        double[] resistanceArray = resistors.stream()
                .mapToDouble(Resistor::getResistance)
                .toArray();

        double denominator = 0d;
        for (Double resistance : resistanceArray) {
            if (resistance <= 0d) {
                throw new InvalidResistorException("resistance can't be 0 or less");
            }
            denominator += 1 / resistance;
        }
        double result = 1 / denominator;
        logger.debug("result = {}", result);
        return result;
    }
}
