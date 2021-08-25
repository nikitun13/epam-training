package by.training.classes.service.impl;

import by.training.classes.entity.Sentence;
import by.training.classes.entity.Text;
import by.training.classes.service.ServiceException;
import by.training.classes.service.TextEditorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code TextEditorServiceImpl}
 * is a class that implements {@link TextEditorService}.
 *
 * @author Nikita Romanov
 * @see TextEditorService
 */
public class TextEditorServiceImpl implements TextEditorService {

    private static final Logger logger
            = LogManager.getLogger(TextEditorServiceImpl.class);

    @Override
    public void addSentence(Text text,
                            int index,
                            Sentence sentence) throws ServiceException {
        logger.debug("received params: text: {}, index = {}, sentence: {}",
                text, index, sentence
        );
        if (text == null
                || sentence == null
                || !isValidIndexToAdd(text, index)) {
            throw new ServiceException(
                    "text or sentence can't be null or invalid index received"
            );
        }
        text.addSentence(index, sentence);
    }

    @Override
    public Sentence removeSentence(Text text,
                                   int index) throws ServiceException {
        logger.debug("received params: text: {}, index = {}",
                text, index
        );
        if (text == null || !isValidIndex(text, index)) {
            throw new ServiceException(
                    "text can't be null or invalid index received"
            );
        }
        return text.removeSentence(index);
    }

    @Override
    public void changeHeader(Text text,
                             Sentence header) throws ServiceException {
        logger.debug("received params: text: {}, header = {}",
                text, header
        );
        if (text == null || header == null) {
            throw new ServiceException("text or header can't be null");
        }
        text.setHeader(header);
    }

    private boolean isValidIndex(Text text, int index) {
        return index >= 0
                && index < text.getNumberOfSentences();
    }

    private boolean isValidIndexToAdd(Text text, int index) {
        return index >= 0
                && index <= text.getNumberOfSentences();
    }
}
