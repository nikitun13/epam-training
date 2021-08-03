package by.training.branching.service.impl;

import by.training.branching.service.DivisorService;
import by.training.branching.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

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
        return null; // stub
    }
}
