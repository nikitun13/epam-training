package by.training.shapes.dao;


import by.training.shapes.dao.impl.BallDaoImpl;
import by.training.shapes.dao.impl.BallRepositoryImpl;
import by.training.shapes.entity.Ball;

/**
 * The class {@code ServiceFactory} is utility class
 * that provides the implementation of services.
 *
 * @author Nikita Romanov
 */
public final class DaoFactory {

    /**
     * Singleton instance.
     */
    private static DaoFactory instance;

    private final BallDao ballDao;
    private final Repository<Ball> ballRepository;

    private DaoFactory() {
        ballDao = new BallDaoImpl();
        ballRepository = new BallRepositoryImpl();
    }

    public BallDao getBallDao() {
        return ballDao;
    }

    public Repository<Ball> getBallRepository() {
        return ballRepository;
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
}
