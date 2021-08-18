package by.training.arrays.dao.impl;

import by.training.arrays.dao.DaoException;
import by.training.arrays.dao.MatrixDao;
import by.training.arrays.entity.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The class {@code ArrayDaoImpl}
 * is a class that implements {@link MatrixDao}.
 * Provides access to {@code Matrix} from file.
 *
 * @author Nikita Romanov
 */
public class MatrixDaoImpl implements MatrixDao {

    private static final Logger logger =
            LogManager.getLogger(MatrixDaoImpl.class);

    private static final String SEPARATOR = "\\s+";

    @Override
    public List<Matrix> readAll(Path path) {
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
            return result;
        } catch (IOException e) {
            throw new DaoException("IO exception occurred", e);
        } catch (RuntimeException e) {
            throw new DaoException("invalid matrix in the file", e);
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

    private Matrix createMatrixFromStringList(List<String> list) {
        int[][] elements = convertToIntArrayOfArrays(list);
        return new Matrix(elements);
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
}
