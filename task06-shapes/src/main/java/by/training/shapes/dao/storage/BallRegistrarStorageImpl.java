package by.training.shapes.dao.storage;

import by.training.shapes.entity.BallRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BallRegistrarStorageImpl
        implements Storage<Integer, BallRegistrar> {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(BallRegistrarStorageImpl.class);
    /**
     * Message for logger about received args.
     */
    private static final String RECEIVED_KEY_AND_VALUE_LOG_MESSAGE
            = "received key: {}, ball: {}";
    /**
     * Singleton instance.
     */
    private static BallRegistrarStorageImpl instance;

    /**
     * Real storage of entities.
     */
    private final Map<Integer, BallRegistrar> dataStorage;

    private BallRegistrarStorageImpl() {
        dataStorage = new HashMap<>();
    }

    @Override
    public BallRegistrar getByKey(final Integer key) {
        log.debug("received key: {}", key);
        BallRegistrar ballRegistrar = dataStorage.get(key);
        log.debug("returning Ball Registrar: {}", ballRegistrar);
        return ballRegistrar;
    }

    @Override
    public List<BallRegistrar> getALlValues() {
        List<BallRegistrar> ballRegistrars = List.copyOf(dataStorage.values());
        log.debug("returning all Ball Registrars: {}", ballRegistrars);
        return ballRegistrars;
    }

    @Override
    public boolean add(final Integer key, final BallRegistrar value) {
        log.debug(RECEIVED_KEY_AND_VALUE_LOG_MESSAGE, key, value);
        BallRegistrar result = dataStorage.putIfAbsent(key, value);
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
    public Map<Integer, BallRegistrar> addAll(
            final Map<Integer, BallRegistrar> items) {
        log.debug("received items: {}", items);
        Map<Integer, BallRegistrar> remaining = new HashMap<>();
        for (Map.Entry<Integer, BallRegistrar> entry : items.entrySet()) {
            if (!add(entry.getKey(), entry.getValue())) {
                remaining.put(entry.getKey(), entry.getValue());
            }
        }
        log.debug("remaining items: {}", remaining);
        return remaining;
    }

    @Override
    public boolean update(final Integer key, final BallRegistrar value) {
        log.debug(RECEIVED_KEY_AND_VALUE_LOG_MESSAGE, key, value);
        BallRegistrar replaceResult = dataStorage.replace(key, value);
        if (replaceResult != null) {
            log.debug("ball registrar: {} associated with key {} was updated",
                    value, key
            );
            return true;
        } else {
            log.debug("no such element with the given key: {}", key);
            return false;
        }
    }

    @Override
    public boolean remove(final Integer key, final BallRegistrar value) {
        log.debug(RECEIVED_KEY_AND_VALUE_LOG_MESSAGE, key, value);
        boolean isRemoved = dataStorage.remove(key, value);
        if (isRemoved) {
            log.debug("ball registrar: {} was removed", value);
        } else {
            log.debug("ball registrar: {} associated with key {} wasn't remove",
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
    public static BallRegistrarStorageImpl getInstance() {
        if (instance == null) {
            instance = new BallRegistrarStorageImpl();
        }
        return instance;
    }
}
