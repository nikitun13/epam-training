package by.training.arrays.controller.command.impl;

import by.training.arrays.controller.command.Command;
import by.training.arrays.controller.command.result.CommandResult;
import by.training.arrays.controller.command.result.CommandStatus;
import by.training.arrays.entity.Matrix;
import by.training.arrays.service.MatrixCreatorService;
import by.training.arrays.service.MatrixOperationsService;
import by.training.arrays.service.ServiceFactory;
import by.training.arrays.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;

/**
 * The class {@code MatrixCommand} is a class
 * that implements {@link Command}.<br/>
 * Executed when the command 'matrix' is received.
 * This command provides matrix operations.
 *
 * @author Nikita Romanov
 */
public class MatrixCommand implements Command {

    private static final Logger logger =
            LogManager.getLogger(MatrixCommand.class);

    private static final String MULTIPLY_TWO_MATRIX_OPERATION =
            "multiply-matrix";
    private static final String MULTIPLY_BY_COEFFICIENT_OPERATION =
            "multiply-coeff";
    private static final String ADD_OPERATION = "add";
    private static final String SUBTRACT_OPERATION = "subtract";
    private static final String TRANSPOSE_OPERATION = "transpose";
    private static final String SIZE_SEPARATOR_REGEX = "x";
    private static final String LEFT_MATRIX_TEXT_KEY = "matrix.leftMatrix";
    private static final String RIGHT_MATRIX_TEXT_KEY = "matrix.rightMatrix";
    private static final String RESULT_TEXT_KEY = "result";

    private final MatrixCreatorService creatorService =
            ServiceFactory.getInstance().getMatrixCreatorService();
    private final MatrixOperationsService operationsService =
            ServiceFactory.getInstance().getMatrixOperationsService();

    @Override
    public CommandResult execute(String[] params) {
        String arrayStatus = Arrays.toString(params);
        logger.debug("received params: {}", arrayStatus);
        CommandResult result;
        if (params.length < 2) {
            logger.error("Invalid number of parameters");
            result = new CommandResult(
                    CommandStatus.INVALID_NUMBER_OF_PARAMETERS
            );
            logger.debug("result: {}", result);
            return result;
        }
        String operationName = params[0];
        result = switch (operationName) {
            case MULTIPLY_TWO_MATRIX_OPERATION -> multiplyTwoMatrix(params);
            case MULTIPLY_BY_COEFFICIENT_OPERATION -> multiplyByCoefficient(params);
            case ADD_OPERATION -> add(params);
            case SUBTRACT_OPERATION -> subtract(params);
            case TRANSPOSE_OPERATION -> transpose(params);
            default -> {
                logger.error("Invalid operation name: {}", operationName);
                yield new CommandResult(
                        CommandStatus.INVALID_INPUT_PARAMETERS,
                        TextManager.getText("matrix.invalidOperationName")
                                + operationName
                );
            }
        };
        logger.debug("result: {}", result);
        return result;
    }

    private CommandResult multiplyTwoMatrix(String[] params) {
        logger.debug("multiply two matrix");
        try {
            List<Matrix> twoMatrixList = getTwoMatrixHelper(params);
            Matrix left = twoMatrixList.get(0);
            Matrix right = twoMatrixList.get(1);
            Matrix resultMatrix =
                    operationsService.multiplyTwoMatrix(left, right);
            return new CommandResult(
                    CommandStatus.OK,
                    TextManager.getText(LEFT_MATRIX_TEXT_KEY)
                            + left
                            + TextManager.getText(RIGHT_MATRIX_TEXT_KEY)
                            + right
                            + TextManager.getText(RESULT_TEXT_KEY)
                            + resultMatrix
            );
        } catch (RuntimeException e) {
            logger.error(e);
            return new CommandResult(
                    CommandStatus.INVALID_INPUT_PARAMETERS
            );
        }
    }

    private CommandResult multiplyByCoefficient(String[] params) {
        logger.debug("multiply by coefficient");
        int length = params.length;
        CommandResult result;
        try {
            Matrix sourceMatrix;
            if (length == 3) {
                String strPath = params[2];
                List<Matrix> matrixList = getFromFile(strPath);
                if (matrixList.size() != 1) {
                    throw new IllegalArgumentException(
                            "expected to get one matrix"
                    );
                }
                sourceMatrix = matrixList.get(0);
            } else if (length == 5) {
                String sizeOfSourceMatrix = params[2];
                sourceMatrix = createUsingSize(sizeOfSourceMatrix);
                String strMinValue = params[3];
                String strMaxValue = params[4];
                int minValue = Integer.parseInt(strMinValue);
                int maxValue = Integer.parseInt(strMaxValue);
                creatorService.fillRandomized(sourceMatrix, minValue, maxValue);
            } else {
                return new CommandResult(
                        CommandStatus.INVALID_NUMBER_OF_PARAMETERS
                );
            }
            int coefficient = Integer.parseInt(params[1]);
            Matrix resultMatrix =
                    operationsService.multiplyByCoefficient(
                            coefficient, sourceMatrix
                    );
            result = new CommandResult(
                    CommandStatus.OK,
                    TextManager.getText("matrix.inputMatrix")
                            + sourceMatrix
                            + TextManager.getText(RESULT_TEXT_KEY)
                            + resultMatrix
            );
        } catch (RuntimeException e) {
            logger.error(e);
            result = new CommandResult(
                    CommandStatus.INVALID_INPUT_PARAMETERS
            );
        }
        return result;
    }

    private CommandResult add(String[] params) {
        logger.debug("add operation");
        try {
            List<Matrix> twoMatrixList = getTwoMatrixHelper(params);
            Matrix left = twoMatrixList.get(0);
            Matrix right = twoMatrixList.get(1);
            Matrix resultMatrix = operationsService.add(left, right);
            return new CommandResult(
                    CommandStatus.OK,
                    TextManager.getText(LEFT_MATRIX_TEXT_KEY)
                            + left
                            + TextManager.getText(RIGHT_MATRIX_TEXT_KEY)
                            + right
                            + TextManager.getText(RESULT_TEXT_KEY)
                            + resultMatrix
            );
        } catch (RuntimeException e) {
            logger.error(e);
            return new CommandResult(
                    CommandStatus.INVALID_INPUT_PARAMETERS
            );
        }
    }

    private CommandResult subtract(String[] params) {
        logger.debug("subtract operation");
        try {
            List<Matrix> twoMatrixList = getTwoMatrixHelper(params);
            Matrix left = twoMatrixList.get(0);
            Matrix right = twoMatrixList.get(1);
            Matrix resultMatrix = operationsService.subtract(left, right);
            return new CommandResult(
                    CommandStatus.OK,
                    TextManager.getText(LEFT_MATRIX_TEXT_KEY)
                            + left
                            + TextManager.getText(RIGHT_MATRIX_TEXT_KEY)
                            + right
                            + TextManager.getText(RESULT_TEXT_KEY)
                            + resultMatrix
            );
        } catch (RuntimeException e) {
            logger.error(e);
            return new CommandResult(
                    CommandStatus.INVALID_INPUT_PARAMETERS
            );
        }
    }

    private CommandResult transpose(String[] params) {
        logger.debug("transpose operation");
        int length = params.length;
        CommandResult result;
        try {
            List<Matrix> sourceMatrixList;
            if (length == 2) {
                String strPath = params[1];
                sourceMatrixList = getFromFile(strPath);
            } else if (length == 4) {
                String sizeOfSourceMatrix = params[1];
                Matrix matrix = createUsingSize(sizeOfSourceMatrix);
                String strMinValue = params[2];
                String strMaxValue = params[3];
                int minValue = Integer.parseInt(strMinValue);
                int maxValue = Integer.parseInt(strMaxValue);
                creatorService.fillRandomized(matrix, minValue, maxValue);
                sourceMatrixList = List.of(matrix);
            } else {
                return new CommandResult(
                        CommandStatus.INVALID_NUMBER_OF_PARAMETERS
                );
            }
            StringBuilder resultString = new StringBuilder();
            sourceMatrixList.stream()
                    .map(matrix -> new SimpleEntry<>(
                            matrix, operationsService.transpose(matrix)
                    ))
                    .forEach(entry -> resultString.append(entry.getKey())
                            .append(TextManager.getText("matrix.transposed"))
                            .append(entry.getValue()));
            result = new CommandResult(
                    CommandStatus.OK,
                    resultString.toString()
            );
        } catch (RuntimeException e) {
            logger.error(e);
            result = new CommandResult(
                    CommandStatus.INVALID_INPUT_PARAMETERS
            );
        }
        return result;
    }

    private List<Matrix> getTwoMatrixHelper(String[] params) {
        int length = params.length;
        Matrix left;
        Matrix right;
        if (length == 2) {
            String strPath = params[1];
            List<Matrix> matrixList = getFromFile(strPath);
            if (matrixList.size() != 2) {
                throw new IllegalArgumentException(
                        "expected to get two matrix"
                );
            }
            left = matrixList.get(0);
            right = matrixList.get(1);
        } else if (length == 5) {
            String sizeOfLeftMatrix = params[1];
            String sizeOfRightMatrix = params[2];
            left = createUsingSize(sizeOfLeftMatrix);
            right = createUsingSize(sizeOfRightMatrix);
            String strMinValue = params[3];
            String strMaxValue = params[4];
            int minValue = Integer.parseInt(strMinValue);
            int maxValue = Integer.parseInt(strMaxValue);
            creatorService.fillRandomized(left, minValue, maxValue);
            creatorService.fillRandomized(right, minValue, maxValue);
        } else {
            throw new IllegalArgumentException("invalid number of parameters");
        }
        return List.of(left, right);
    }

    private List<Matrix> getFromFile(String strPath) {
        Path path = Path.of(strPath);
        return creatorService.createFromFile(path);
    }

    private Matrix createUsingSize(String size) {
        String[] split = size.split(SIZE_SEPARATOR_REGEX);
        int rows = Integer.parseInt(split[0]);
        int columns = Integer.parseInt(split[1]);
        return new Matrix(rows, columns);
    }
}
