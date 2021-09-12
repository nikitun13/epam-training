package by.training.multithreading.service.validator.impl;

import by.training.multithreading.service.validator.Validator;
import by.training.multithreading.util.PropertiesUtil;

import java.util.HashSet;
import java.util.List;

/**
 * The class {@code ThreadsConfigValidator}
 * is a class that implements {@link Validator}.<br/>
 * Validates {@code ThreadsConfig} list.
 * Valid {@code ThreadsConfig} is a list that contains
 * unique integers that greater than {@code 0}
 * and number of configs within the bounds that are specified
 * in the {@code application.properties} file.
 *
 * @author Nikita Romanov
 * @see Validator
 */
public class ThreadsConfigValidator implements Validator<List<Integer>> {

    private static final ThreadsConfigValidator INSTANCE
            = new ThreadsConfigValidator();

    @Override
    public boolean isValid(List<Integer> o) {
        if (o == null) {
            return false;
        }
        int numberOfThreads = o.size();
        int minNumber = Integer.parseInt(
                PropertiesUtil.getProperty("thread.minNumber")
        );
        int maxNumber = Integer.parseInt(
                PropertiesUtil.getProperty("thread.maxNumber")
        );
        if (!(minNumber <= numberOfThreads && numberOfThreads <= maxNumber)) {
            return false;
        }
        int uniqueSize = new HashSet<>(o).size();
        if (uniqueSize != numberOfThreads) {
            return false;
        }
        for (Integer value : o) {
            if (value <= 0) {
                return false;
            }
        }
        return true;
    }

    public static ThreadsConfigValidator getInstance() {
        return INSTANCE;
    }
}
