package by.training.arrays.dao;

import by.training.arrays.dao.impl.ArrayDaoImpl;
import by.training.arrays.dao.impl.MatrixDaoImpl;

/**
 * The class {@code DaoFactory} is utility class
 * that provides the implementation of DAO interfaces.
 *
 * @author Nikita Romanov
 */
public final class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();

    private final ArrayDao arrayDAO = new ArrayDaoImpl();
    private final MatrixDao matrixDAO = new MatrixDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    public ArrayDao getArrayDAO() {
        return arrayDAO;
    }

    public MatrixDao getMatrixDAO() {
        return matrixDAO;
    }
}
