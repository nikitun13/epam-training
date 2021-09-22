package by.training.shapes.dao.storage;

import java.util.List;
import java.util.Map;

/**
 * Describe interface of a storage
 * that stores entities.
 *
 * @param <K> type of the entity.
 * @param <V> type of the entity itself.
 * @author Nikita Romanov
 */
public interface Storage<K, V> {

    /**
     * Returns entity associated with {@code key}.
     *
     * @param key entity's key.
     * @return entity associated with {@code key}.
     */
    V getByKey(K key);

    /**
     * Returns all entities.
     *
     * @return list of all entities.
     */
    List<V> getALlValues();

    /**
     * Adds to the storage new entity.<br/>
     * The entity won't be added if given {@code key} already exists.
     *
     * @param key   key that will be associated with entity.
     * @param value entity itself.
     * @return {@code true} if entity with given key was added
     * successfully, {@code false} otherwise.
     */
    boolean add(K key, V value);

    /**
     * Adds to the storage new entities.<br/>
     * The entity won't be added if given {@code key} already exists.
     *
     * @param items map of entities.
     * @return map of entities that weren't added.
     */
    Map<K, V> addAll(Map<K, V> items);

    /**
     * Updates entity with the given {@code key}.<br/>
     * The entity won't be added if given {@code key} doesn't exist.
     *
     * @param key   key of the entity that need to be updated.
     * @param value new entity.
     * @return {@code true} if entity with given key was updated
     * successfully, {@code false} otherwise.
     */
    boolean update(K key, V value);

    /**
     * Removes entity from the storage.<br/>
     * The entity won't be removed if given {@code key}
     * isn't associated with the given entity.
     *
     * @param key   key of the entity that need to be removed.
     * @param value entity itself.
     * @return {@code true} if entity with given key was removed
     * successfully, {@code false} otherwise.
     */
    boolean remove(K key, V value);

    /**
     * Removes all entities from the storage.
     */
    void removeAll();
}
