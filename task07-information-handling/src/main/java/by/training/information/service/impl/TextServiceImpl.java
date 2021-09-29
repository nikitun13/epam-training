package by.training.information.service.impl;

import by.training.information.dao.DaoException;
import by.training.information.dao.DaoFactory;
import by.training.information.dao.TextDao;
import by.training.information.entity.TextComposite;
import by.training.information.service.ServiceException;
import by.training.information.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;

/**
 * The class {@code TextServiceImpl}
 * is a class that implements {@link TextService}.
 *
 * @author Nikita Romanov
 * @see TextService
 */
public class TextServiceImpl implements TextService {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(TextServiceImpl.class);

    /**
     * DAO for text.
     */
    private final TextDao dao = DaoFactory.getInstance().getTextDao();

    @Override
    public TextComposite readTextFromFile(final String pathToFile)
            throws ServiceException {
        log.debug("received pathToFile: {}", pathToFile);
        if (pathToFile == null) {
            throw new ServiceException("path can't be null");
        }
        try {
            Path path = Path.of(pathToFile);
            return dao.read(path);
        } catch (InvalidPathException e) {
            throw new ServiceException("invalid path: " + pathToFile, e);
        } catch (DaoException e) {
            throw new ServiceException("DAO exception occurred", e);
        }
    }
}
