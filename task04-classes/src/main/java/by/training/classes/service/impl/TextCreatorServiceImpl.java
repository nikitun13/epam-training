package by.training.classes.service.impl;

import by.training.classes.entity.Sentence;
import by.training.classes.entity.Text;
import by.training.classes.service.SentenceCreatorService;
import by.training.classes.service.ServiceException;
import by.training.classes.service.ServiceFactory;
import by.training.classes.service.TextCreatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * The class {@code TextCreatorServiceImpl}
 * is a class that implements {@link TextCreatorService}.
 *
 * @author Nikita Romanov
 */
public class TextCreatorServiceImpl implements TextCreatorService {

    private static final Logger logger
            = LogManager.getLogger(TextCreatorServiceImpl.class);
    private static final String SEPARATOR_REGEX = "\\.";

    private final SentenceCreatorService sentenceCreator;

    public TextCreatorServiceImpl() {
        ServiceFactory factory = ServiceFactory.getInstance();
        sentenceCreator = factory.getSentenceCreatorService();
    }

    public TextCreatorServiceImpl(SentenceCreatorService sentenceCreator) {
        this.sentenceCreator = sentenceCreator;
    }

    @Override
    public Text createText(String value) throws ServiceException {
        logger.debug("received value: {}", value);
        int indexOfEndOfHeader;
        if (value == null || value.isBlank() || (indexOfEndOfHeader = value.indexOf("\n\n")) == -1) {
            throw new ServiceException("invalid value: " + value);

        }
        String headerValue = value.substring(0, indexOfEndOfHeader);
        Sentence header = sentenceCreator.createSentence(headerValue);
        logger.debug("header: {}", header);
        String body = value.lines()
                .skip(2)
                .collect(joining("\n"));
        String[] sentenceValues = body.split(SEPARATOR_REGEX);
        List<Sentence> sentences = new ArrayList<>(sentenceValues.length);
        for (String sentenceValue : sentenceValues) {
            Sentence sentence = sentenceCreator.createSentence(sentenceValue);
            sentences.add(sentence);
        }
        logger.debug("list of sentences: {}", sentences);
        Text result = new Text(header, sentences);
        logger.debug("result: {}", result);
        return result;
    }
}
