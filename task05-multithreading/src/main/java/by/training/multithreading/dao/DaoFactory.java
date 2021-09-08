package by.training.multithreading.dao;

import by.training.multithreading.dao.impl.MatrixDaoImpl;

/**
 * The class {@code DaoFactory} is utility class
 * that provides the implementation of DAO interfaces.
 *
 * @author Nikita Romanov
 */
public final class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();

    private final MatrixDao matrixDAO = new MatrixDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    public MatrixDao getMatrixDAO() {
        return matrixDAO;
    }
}
