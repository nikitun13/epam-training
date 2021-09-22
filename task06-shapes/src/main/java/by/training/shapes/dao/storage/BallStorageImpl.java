package by.training.shapes.dao.storage;

import by.training.shapes.entity.Ball;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class {@code BallStorageImpl} is a class
 * that implements {@link Storage}.<br/>
 * Stores {@link Ball} entities.
 *
 * @author Nikita Romanov
 * @see Storage
 * @see Ball
 */
public final class BallStorageImpl implements Storage<Integer, Ball> {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(BallStorageImpl.class);
    /**
     * Message for logger about received args.
     */
    private static final String RECEIVED_KEY_AND_VALUE_LOG_MESSAGE
            = "received key: {}, ball: {}";
    /**
     * Singleton instance.
     */
    private static BallStorageImpl instance;

    /**
     * Real storage of entities.
     */
    private final Map<Integer, Ball> dataStorage;

    private BallStorageImpl() {
        dataStorage = new HashMap<>();
    }

    @Override
    public Ball getByKey(final Integer key) {
        log.debug("received key: {}", key);
        Ball ball = dataStorage.get(key);
        log.debug("returning ball: {}", ball);
        return ball;
    }

    @Override
    public List<Ball> getALlValues() {
        List<Ball> balls = List.copyOf(dataStorage.values());
        log.debug("returning all balls: {}", balls);
        return balls;
    }

    @Override
    public boolean add(final Integer key, final Ball value) {
        log.debug(RECEIVED_KEY_AND_VALUE_LOG_MESSAGE, key, value);
        var result = dataStorage.putIfAbsent(key, value);
        if (result == null) {
            log.debug("successfully added {}. Current state of storage: {}",
                    value, dataStorage
            );
            return true;
        } else {
            log.debug("key {} already associated with: {}", key, result);
            return false;
        }
    }

    @Override
    public Map<Integer, Ball> addAll(final Map<Integer, Ball> items) {
        log.debug("received items: {}", items);
        Map<Integer, Ball> remaining = new HashMap<>();
        for (Map.Entry<Integer, Ball> entry : items.entrySet()) {
            if (!add(entry.getKey(), entry.getValue())) {
                remaining.put(entry.getKey(), entry.getValue());
            }
        }
        log.debug("remaining items: {}", remaining);
        return remaining;
    }

    @Override
    public boolean update(final Integer key, final Ball value) {
        log.debug(RECEIVED_KEY_AND_VALUE_LOG_MESSAGE, key, value);
        Ball replaceResult = dataStorage.replace(key, value);
        if (replaceResult != null) {
            log.debug("ball: {} associated with key {} was updated",
                    value, key
            );
            return true;
        } else {
            log.debug("no such element with the given key: {}", key);
            return false;
        }
    }

    @Override
    public boolean remove(final Integer key, final Ball value) {
        log.debug(RECEIVED_KEY_AND_VALUE_LOG_MESSAGE, key, value);
        boolean isRemoved = dataStorage.remove(key, value);
        if (isRemoved) {
            log.debug("ball: {} was removed", value);
        } else {
            log.debug("ball: {} associated with key {} wasn't remove",
                    value, key
            );
        }
        return isRemoved;
    }

    @Override
    public void removeAll() {
        dataStorage.clear();
    }

    /**
     * Getter for singleton instance.
     *
     * @return instance of this class.
     */
    public static BallStorageImpl getInstance() {
        if (instance == null) {
            instance = new BallStorageImpl();
        }
        return instance;
    }
}
