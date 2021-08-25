package by.training.classes.service.impl;

import by.training.classes.entity.Word;
import by.training.classes.service.ServiceException;
import by.training.classes.service.WordCreatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code WordCreatorServiceImpl}
 * is a class that implements {@link WordCreatorService}.
 *
 * @author Nikita Romanov
 */
public class WordCreatorServiceImpl implements WordCreatorService {

    private static final Logger logger
            = LogManager.getLogger(WordCreatorServiceImpl.class);

    @Override
    public Word createWord(String value) throws ServiceException {
        logger.debug("received value: {}", value);
        if (value == null || value.isBlank()) {
            throw new ServiceException("invalid value: " + value);
        }
        Word result = new Word(value);
        logger.debug("result Word: {}", result);
        return result;
    }
}
