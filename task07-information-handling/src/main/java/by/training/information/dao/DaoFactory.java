package by.training.information.dao;

import by.training.information.dao.impl.TextDaoImpl;

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

    private final TextDao textDao;

    private DaoFactory() {
        textDao = new TextDaoImpl();
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

    /**
     * Getter.
     *
     * @return text DAO.
     */
    public TextDao getTextDao() {
        return textDao;
    }
}
