package by.training.xml.dao;

import by.training.xml.dao.impl.GemXmlDaoImpl;
import by.training.xml.entity.Gem;

/**
 * The class {@code DaoFactory} is utility class
 * that provides the implementation of DAOs.
 *
 * @author Nikita Romanov
 */
public final class DaoFactory {

    /**
     * Singleton instance.
     */
    private static DaoFactory instance;

    private final XmlDao<Gem> gemXmlDao = new GemXmlDaoImpl();

    private DaoFactory() {
    }

    /**
     * Getter for singleton instance.
     *
     * @return instance of this class.
     */
    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    public XmlDao<Gem> getGemXmlDao() {
        return gemXmlDao;
    }
}
