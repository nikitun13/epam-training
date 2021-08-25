package by.training.classes.service.impl;

import by.training.classes.dao.DaoException;
import by.training.classes.dao.DaoFactory;
import by.training.classes.dao.TextDao;
import by.training.classes.entity.Text;
import by.training.classes.service.ServiceException;
import by.training.classes.service.ServiceFactory;
import by.training.classes.service.TextCreatorService;
import by.training.classes.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

import static java.util.stream.Collectors.joining;

/**
 * The class {@code TextServiceImpl}
 * is a class that implements {@link TextService}.
 *
 * @author Nikita Romanov
 * @see TextService
 */
public class TextServiceImpl implements TextService {

    private static final Logger logger
            = LogManager.getLogger(TextServiceImpl.class);

    private final TextDao textDao;
    private final TextCreatorService textCreator;

    public TextServiceImpl() {
        textCreator = ServiceFactory.getInstance().getTextCreatorService();
        textDao = DaoFactory.getInstance().getTextDao();
    }

    public TextServiceImpl(TextDao textDao, TextCreatorService textCreator) {
        this.textDao = textDao;
        this.textCreator = textCreator;
    }

    @Override
    public Text readText(Path path) throws ServiceException {
        logger.debug("received path: {}", path);
        if (path == null) {
            throw new ServiceException("path can't be null");
        }
        try {
            String textValue = textDao.read(path);
            logger.debug("read result: {}", textValue);
            Text text = textCreator.createText(textValue);
            logger.debug("parsing result: {}", text);
            return text;
        } catch (DaoException e) {
            throw new ServiceException("read exception occurred", e);
        }
    }

    @Override
    public void saveText(Path path, Text text) throws ServiceException {
        logger.debug("received path: {}, text: {}", path, text);
        if (path == null || text == null) {
            throw new ServiceException("path or text can't be null");
        }
        try {
            String textValue = text.toString().lines()
                    .map(String::strip)
                    .collect(joining("\n"));
            textDao.save(path, textValue);
        } catch (DaoException e) {
            throw new ServiceException("save exception occurred", e);
        }
    }

    @Override
    public void deleteText(Path path) throws ServiceException {
        logger.debug("received path: {}", path);
        if (path == null) {
            throw new ServiceException("path can't be null");
        }
        try {
            textDao.delete(path);
        } catch (DaoException e) {
            throw new ServiceException("delete exception occurred", e);
        }
    }
}
