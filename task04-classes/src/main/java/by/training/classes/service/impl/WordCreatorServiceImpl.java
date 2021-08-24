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
        if (value == null || value.isBlank()) {
            throw new ServiceException("value can't be null or blank");
        }
        logger.debug("received value: {}", value);
        Word result = new Word(value);
        logger.debug("result: {}", result);
        return result;
    }
}
