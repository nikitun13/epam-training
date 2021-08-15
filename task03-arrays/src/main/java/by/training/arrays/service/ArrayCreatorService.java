package by.training.arrays.service;

import by.training.arrays.entity.Array;
import by.training.arrays.service.exception.ServiceException;

import java.nio.file.Path;
import java.util.List;

/**
 * Describes the interface of a service
 * that creates {@link Array}.
 *
 * @author Nikita Romanov
 * @see Array
 */
public interface ArrayCreatorService {

    /**
     * Creates new {@code Array} and fills it with
     * random values in an inclusive range from {@code minValue}
     * to {@code maxValue}.
     *
     * @param size     size of new {@code Array}.
     * @param minValue minimum value of random numbers inclusive.
     * @param maxValue maximum value of random numbers inclusive.
     * @return filled Array with random values.
     * @throws ServiceException if {@code minValue} is greater
     *                          than {@code maxValue}
     *                          or {@code size} is less than 0.
     */
    Array<Integer> fillRandomized(int size, int minValue, int maxValue);

    /**
     * Creates list of {@code Array} of {@code String}
     * with values that are contained in the file.<br>
     * Different {@code Arrays} in the file must be separated
     * by an empty line.
     *
     * @param path path to file.
     * @return list of {@code Array} with values
     * that are contained in the file.
     * @throws ServiceException     if {@code Array} in the file is invalid
     *                              or IO exception is occurred.
     * @throws NullPointerException if {@code path} is {@code null}.
     */
    List<Array<String>> createFromFile(Path path);

    /**
     * Converts {@code Array of Strings} to
     * {@code Array of Integers}.
     *
     * @param array {@code Array of Strings} to convert.
     * @return result of converting.
     */
    Array<Integer> convertToIntegerArray(Array<String> array);
}
