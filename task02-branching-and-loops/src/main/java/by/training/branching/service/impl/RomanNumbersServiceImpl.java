package by.training.branching.service.impl;

import by.training.branching.entity.RomanInteger;
import by.training.branching.service.RomanNumbersService;
import by.training.branching.service.ServiceException;
import by.training.branching.service.validator.RomanNumbersValidator;
import by.training.branching.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * The class {@code RomanNumbersServiceImpl} is a class that implements {@link RomanNumbersService}.
 *
 * @author Nikita Romanov
 */
public class RomanNumbersServiceImpl implements RomanNumbersService {

    private static final Logger logger = LogManager.getLogger(RomanNumbersServiceImpl.class);

    private static final Validator<RomanInteger> validator = new RomanNumbersValidator();
    private static final Map<Integer, RomanInteger> DEFAULT_ROMAN_VALUES = new TreeMap<>(Comparator.reverseOrder());

    static {
        DEFAULT_ROMAN_VALUES.put(1000, new RomanInteger("M"));
        DEFAULT_ROMAN_VALUES.put(900, new RomanInteger("CM"));
        DEFAULT_ROMAN_VALUES.put(500, new RomanInteger("D"));
        DEFAULT_ROMAN_VALUES.put(400, new RomanInteger("CD"));
        DEFAULT_ROMAN_VALUES.put(100, new RomanInteger("C"));
        DEFAULT_ROMAN_VALUES.put(90, new RomanInteger("XC"));
        DEFAULT_ROMAN_VALUES.put(50, new RomanInteger("L"));
        DEFAULT_ROMAN_VALUES.put(40, new RomanInteger("XL"));
        DEFAULT_ROMAN_VALUES.put(10, new RomanInteger("X"));
        DEFAULT_ROMAN_VALUES.put(9, new RomanInteger("IX"));
        DEFAULT_ROMAN_VALUES.put(5, new RomanInteger("V"));
        DEFAULT_ROMAN_VALUES.put(4, new RomanInteger("IV"));
        DEFAULT_ROMAN_VALUES.put(1, new RomanInteger("I"));
    }


    @Override
    public int fromRoman(RomanInteger romanNumber) {
        logger.debug("received: {}", romanNumber);
        Objects.requireNonNull(romanNumber);
        if (!validator.isValid(romanNumber)) {
            throw new ServiceException("invalid roman number: " + romanNumber);
        }
        int result = 0;
        String stringRomanValue = romanNumber.getValue();
        for (Map.Entry<Integer, RomanInteger> entry : DEFAULT_ROMAN_VALUES.entrySet()) {
            String defaultRomanStringValue = entry.getValue().getValue();
            while (stringRomanValue.startsWith(defaultRomanStringValue)) {
                result += entry.getKey();
                stringRomanValue = stringRomanValue.substring(defaultRomanStringValue.length());
            }
        }
        logger.debug("result: {}", result);
        return result;
    }
}
