package by.training.classes.dao;

import by.training.classes.dao.impl.TextDaoImpl;

/**
 * The class {@code DaoFactory} is utility class
 * that provides the implementation of DAO interfaces.
 *
 * @author Nikita Romanov
 */
public final class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();

    private final TextDao textDao = new TextDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    public TextDao getTextDao() {
        return textDao;
    }
}
