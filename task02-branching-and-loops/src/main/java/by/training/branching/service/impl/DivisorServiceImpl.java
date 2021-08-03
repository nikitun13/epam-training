package by.training.branching.service.impl;

import by.training.branching.service.DivisorService;
import by.training.branching.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * The class {@code DivisorServiceImpl} is a class that implements {@link DivisorService}.
 *
 * @author Nikita Romanov
 */
public class DivisorServiceImpl implements DivisorService {

    private static final Logger logger = LogManager.getLogger(DivisorServiceImpl.class);

    @Override
    public List<Integer> findDivisibleNumbers(int divisor, List<Integer> numbers) {
        logger.debug("received: divisor = {}, numbers: {}", divisor, numbers);
        if (divisor == 0) {
            throw new ServiceException("divisor can't be 0");
        }
        Objects.requireNonNull(numbers);
        numbers.forEach(Objects::requireNonNull);
        List<Integer> result = numbers.stream()
                .filter(number -> number % divisor == 0)
                .toList();
        logger.debug("result: {}", result);
        return result;
    }

    @Override
    public List<Integer> findAllNumbersDivisibleByTheirDigits(int bound) {
        logger.debug("received bound: {}", bound);
        if (bound <= 0) {
            throw new ServiceException("bound can't be 0 or less");
        }
        List<Integer> result = Stream.iterate(1, integer -> integer <= bound, integer -> integer += 1)
                .filter(DivisorServiceImpl::isDivisibleByOwnDigits)
                .toList();
        logger.debug("result: {}", result);
        return result;
    }

    private static boolean isDivisibleByOwnDigits(Integer number) {
        String stringNumber = number.toString();
        int[] result = stringNumber.chars()
                .map(codePoint -> Character.digit(codePoint, 10))
                .filter(digit -> digit != 0)
                .filter(digit -> number % digit == 0)
                .toArray();
        return stringNumber.length() == result.length;
    }
}
