package by.training.arrays.service.impl;

import by.training.arrays.entity.Matrix;
import by.training.arrays.service.MatrixCreatorService;
import by.training.arrays.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * The class {@code MatrixCreatorServiceImpl}
 * is a class that implements {@link MatrixCreatorService}.
 *
 * @author Nikita Romanov
 */
public class MatrixCreatorServiceImpl implements MatrixCreatorService {

    private static final Logger logger =
            LogManager.getLogger(MatrixCreatorServiceImpl.class);

    private static final Random RANDOM = new Random();
    private static final String SEPARATOR = "\\s+";

    @Override
    public void fillRandomized(Matrix matrix, int minValue, int maxValue) {
        logger.debug("received minValue = {}, maxValue = {} and matrix: {}",
                minValue, maxValue, matrix);
        Objects.requireNonNull(matrix);
        int diff = maxValue - minValue;
        if (diff < 0) {
            throw new ServiceException(String.format(
                    "maxValue %d can't be less than minValue %d",
                    maxValue, minValue)
            );
        }
        int rows = matrix.getNumberOfRows();
        int columns = matrix.getNumberOfColumns();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int value = RANDOM.nextInt(diff + 1) + minValue;
                matrix.setElement(i, j, value);
            }
        }
        logger.debug("result: {}", matrix);
    }

    @Override
    public List<Matrix> createFromFile(Path path) {
        logger.debug("received path: {}", path);
        Objects.requireNonNull(path);
        try {
            List<String> allLines = Files.readAllLines(path);
            List<Matrix> result = new ArrayList<>();
            int currentIndex = 0;
            int indexOfSeparator;
            while ((indexOfSeparator =
                    findIndexOfSeparator(allLines, currentIndex))
                    >= currentIndex) {
                List<String> subList =
                        allLines.subList(currentIndex, indexOfSeparator);
                logger.debug("subList: {}", subList);
                Matrix matrix = createMatrixFromStringList(subList);
                result.add(matrix);
                logger.debug("added to list: {}", matrix);
                currentIndex = indexOfSeparator + 1;
            }
            logger.debug("result: {}", result);
            return result;
        } catch (IOException e) {
            throw new ServiceException("IO exception occurred", e);
        } catch (RuntimeException e) {
            throw new ServiceException("invalid matrix in the file", e);
        }
    }

    private int findIndexOfSeparator(List<String> list, int startFrom) {
        logger.debug("received start index = {}, list: {}", startFrom, list);
        int size = list.size();
        for (int i = startFrom; i < size; i++) {
            if (list.get(i).isBlank()) {
                logger.debug("index of empty line: {}", i);
                return i;
            }
        }
        logger.debug("no empty lines, return end of file index: {}", size);
        return size;
    }

    private int[][] convertToIntArrayOfArrays(List<String> list) {
        logger.debug("received: {}", list);
        return list.stream()
                .map(line -> line.split(SEPARATOR))
                .map(array -> Arrays.stream(array)
                        .mapToInt(Integer::parseInt)
                        .toArray()
                )
                .toArray(int[][]::new);
    }

    private Matrix createMatrixFromStringList(List<String> list) {
        int[][] elements = convertToIntArrayOfArrays(list);
        return new Matrix(elements);
    }
}
