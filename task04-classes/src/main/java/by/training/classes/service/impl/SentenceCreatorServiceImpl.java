package by.training.classes.service.impl;

import by.training.classes.entity.Sentence;
import by.training.classes.entity.Word;
import by.training.classes.service.SentenceCreatorService;
import by.training.classes.service.ServiceException;
import by.training.classes.service.ServiceFactory;
import by.training.classes.service.WordCreatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The class {@code SentenceCreatorServiceImpl}
 * is a class that implements {@link SentenceCreatorService}.
 *
 * @author Nikita Romanov
 */
public class SentenceCreatorServiceImpl implements SentenceCreatorService {

    private static final Logger logger
            = LogManager.getLogger(SentenceCreatorServiceImpl.class);
    private static final String SEPARATOR_REGEX = " +";

    private final WordCreatorService wordCreator;

    public SentenceCreatorServiceImpl() {
        wordCreator = ServiceFactory.getInstance().getWordCreatorService();
    }

    public SentenceCreatorServiceImpl(WordCreatorService wordCreator) {
        this.wordCreator = wordCreator;
    }

    @Override
    public Sentence createSentence(String value) throws ServiceException {
        logger.debug("received value: {}", value);
        if (value == null || value.isBlank()) {
            throw new ServiceException("invalid value: " + value);
        }
        if (value.charAt(0) == ' ') {
            value = value.replaceFirst(" ", "");
        }
        String[] wordValues = value.split(SEPARATOR_REGEX);
        List<Word> words = new ArrayList<>(wordValues.length);
        for (String wordValue : wordValues) {
            Word word = wordCreator.createWord(wordValue);
            words.add(word);
        }
        logger.debug("list of words: {}", words);
        Sentence result = new Sentence(words);
        logger.debug("result Sentence: {}", result);
        return result;
    }
}
