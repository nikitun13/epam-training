package by.training.multithreading.service.validator.impl;

import by.training.multithreading.entity.Matrix;
import by.training.multithreading.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code SquareMatrixWithEmptyMainDiagonalValidator}
 * is a class that implements {@link Validator}.<br/>
 * Validates {@link Matrix} entity.
 * Valid {@code matrix} are square {@code matrix} with empty main diagonal
 * (main diagonal contains only zeros).
 *
 * @author Nikita Romanov
 * @see Matrix
 * @see Validator
 */
public final class SquareMatrixWithEmptyMainDiagonalValidator
        implements Validator<Matrix> {

    private static final SquareMatrixWithEmptyMainDiagonalValidator INSTANCE
            = new SquareMatrixWithEmptyMainDiagonalValidator();


    private static final Logger log = LogManager.getLogger(
            SquareMatrixWithEmptyMainDiagonalValidator.class
    );

    private SquareMatrixWithEmptyMainDiagonalValidator() {
    }

    @Override
    public boolean isValid(Matrix o) {
        log.debug("received: {}", o);
        if (o == null || o.getNumberOfColumns() != o.getNumberOfRows()) {
            return false;
        }
        for (int i = 0; i < o.getNumberOfRows(); i++) {
            if (o.getElement(i, i) != 0) {
                return false;
            }
        }
        return true;
    }

    public static SquareMatrixWithEmptyMainDiagonalValidator getInstance() {
        return INSTANCE;
    }
}
