package by.training.arrays.service.impl;

import by.training.arrays.entity.Matrix;
import by.training.arrays.service.MatrixOperationsService;
import by.training.arrays.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * The class {@code MatrixOperationsServiceImpl}
 * is a class that implements {@link MatrixOperationsService}.
 *
 * @author Nikita Romanov
 */
public class MatrixOperationsServiceImpl implements MatrixOperationsService {

    private static final Logger logger = LogManager.getLogger(MatrixOperationsServiceImpl.class);

    private static final int PLUS_COEFFICIENT = 1;
    private static final int MINUS_COEFFICIENT = -1;

    private static final String RECEIVED_TWO_MATRIX_LOGGER_MESSAGE =
            "received leftMatrix: {} and rightMatrix: {}";
    private static final String CREATED_MATRIX_LOGGER_MESSAGE =
            "create new matrix {}x{}";
    private static final String RESULT_LOGGER_MESSAGE =
            "result: {}";
    private static final String ELEMENT_IS_EQUAL_TO_LOGGER_MESSAGE =
            "element with indexes [{}][{}] = {}";

    @Override
    public Matrix multiplyTwoMatrix(Matrix leftMatrix, Matrix rightMatrix) {
        logger.debug(RECEIVED_TWO_MATRIX_LOGGER_MESSAGE,
                leftMatrix, rightMatrix
        );
        Objects.requireNonNull(leftMatrix);
        Objects.requireNonNull(rightMatrix);
        if (leftMatrix.getNumberOfColumns() != rightMatrix.getNumberOfRows()) {
            throw new ServiceException("incompatible matrix");
        }
        int rows = leftMatrix.getNumberOfRows();
        int columns = rightMatrix.getNumberOfColumns();
        Matrix result = new Matrix(rows, columns);
        logger.debug(CREATED_MATRIX_LOGGER_MESSAGE, rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int value = 0;
                for (int k = 0; k < leftMatrix.getNumberOfColumns(); k++) {
                    value += leftMatrix.getElement(i, k) * rightMatrix.getElement(k, j);
                }
                logger.debug(ELEMENT_IS_EQUAL_TO_LOGGER_MESSAGE, i, j, value);
                result.setElement(i, j, value);
            }
        }
        logger.debug(RESULT_LOGGER_MESSAGE, result);
        return result;
    }

    @Override
    public Matrix multiplyByCoefficient(int coefficient, Matrix matrix) {
        logger.debug("received coefficient = {} and matrix: {}",
                coefficient, matrix
        );
        Objects.requireNonNull(matrix);
        int rows = matrix.getNumberOfRows();
        int columns = matrix.getNumberOfColumns();
        Matrix result = new Matrix(rows, columns);
        logger.debug(CREATED_MATRIX_LOGGER_MESSAGE, rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int value = coefficient * matrix.getElement(i, j);
                logger.debug(ELEMENT_IS_EQUAL_TO_LOGGER_MESSAGE, i, j, value);
                result.setElement(i, j, value);
            }
        }
        logger.debug(RESULT_LOGGER_MESSAGE, result);
        return result;
    }

    @Override
    public Matrix add(Matrix leftMatrix, Matrix rightMatrix) {
        logger.debug(RECEIVED_TWO_MATRIX_LOGGER_MESSAGE,
                leftMatrix, rightMatrix
        );
        Matrix result = addHelper(leftMatrix, rightMatrix, PLUS_COEFFICIENT);
        logger.debug(RESULT_LOGGER_MESSAGE, result);
        return result;
    }

    @Override
    public Matrix subtract(Matrix leftMatrix, Matrix rightMatrix) {
        logger.debug(RECEIVED_TWO_MATRIX_LOGGER_MESSAGE,
                leftMatrix, rightMatrix
        );
        Matrix result = addHelper(leftMatrix, rightMatrix, MINUS_COEFFICIENT);
        logger.debug(RESULT_LOGGER_MESSAGE, result);
        return result;
    }

    @Override
    public Matrix transpose(Matrix matrix) {
        logger.debug("received: {}", matrix);
        Objects.requireNonNull(matrix);
        int rows = matrix.getNumberOfRows();
        int columns = matrix.getNumberOfColumns();
        Matrix result = new Matrix(columns, rows);
        logger.debug(CREATED_MATRIX_LOGGER_MESSAGE, columns, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int value = matrix.getElement(i, j);
                logger.debug(ELEMENT_IS_EQUAL_TO_LOGGER_MESSAGE, j, i, value);
                result.setElement(j, i, value);
            }
        }
        logger.debug(RESULT_LOGGER_MESSAGE, result);
        return result;
    }

    private Matrix addHelper(Matrix leftMatrix, Matrix rightMatrix, int operatorCoefficient) {
        Objects.requireNonNull(leftMatrix);
        Objects.requireNonNull(rightMatrix);
        if (!isEqualSize(leftMatrix, rightMatrix)) {
            throw new ServiceException("incompatible matrix");
        }
        int rows = leftMatrix.getNumberOfRows();
        int columns = leftMatrix.getNumberOfColumns();
        Matrix result = new Matrix(rows, columns);
        logger.debug(CREATED_MATRIX_LOGGER_MESSAGE, rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int leftValue = leftMatrix.getElement(i, j);
                int rightValue = rightMatrix.getElement(i, j);
                int resultValue = leftValue + operatorCoefficient * rightValue;
                logger.debug(ELEMENT_IS_EQUAL_TO_LOGGER_MESSAGE, i, j, resultValue);
                result.setElement(i, j, resultValue);
            }
        }
        return result;
    }

    private boolean isEqualSize(Matrix first, Matrix second) {
        return first.getNumberOfColumns() == second.getNumberOfColumns()
                && first.getNumberOfRows() == second.getNumberOfRows();
    }
}
