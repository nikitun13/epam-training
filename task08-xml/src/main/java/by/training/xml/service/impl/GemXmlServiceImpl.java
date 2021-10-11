package by.training.xml.service.impl;

import by.training.xml.dao.DaoException;
import by.training.xml.dao.DaoFactory;
import by.training.xml.dao.XmlDao;
import by.training.xml.entity.Gem;
import by.training.xml.service.ServiceException;
import by.training.xml.service.XmlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class {@code GemXmlServiceImpl} is a class
 * that implements {@link XmlService}.
 *
 * @author Nikita Romanov
 * @see XmlService
 */
public class GemXmlServiceImpl implements XmlService<Gem> {

    private static final Logger log
            = LogManager.getLogger(GemXmlServiceImpl.class);

    private final XmlDao<Gem> dao;

    public GemXmlServiceImpl() {
        dao = DaoFactory.getInstance().getGemXmlDao();
    }

    @Override
    public List<Gem> findEntities(final String pathToXml,
                                  final String parserName)
            throws ServiceException {
        log.debug("received pathToXml: {}, parserName: {}",
                pathToXml, parserName
        );
        if (isInvalidString(parserName) || isInvalidString(pathToXml)) {
            throw new ServiceException("invalid input");
        }
        try {
            return dao.loadEntities(pathToXml, parserName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean isInvalidString(final String string) {
        return string == null || string.isBlank();
    }
}
