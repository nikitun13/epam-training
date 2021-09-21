package by.training.shapes.dao.mapper;

/**
 * Describes interface of a class that
 * converts a string to an entity object.
 *
 * @param <T> the type of entity to convert to.
 * @author Nikita Romanov
 */
public interface Mapper<T> {

    /**
     * Checks if the {@code line} is valid for converting.<br/>
     * Should be called before calling {@code mapFrom(String)} method.
     *
     * @param line the {@code line} to be validated.
     * @return {@code true} if the {@code line} is valid,
     * {@code false} otherwise.
     */
    boolean isValidLine(String line);

    /**
     * Converts the {@code line} to entity {@code T}.<br/>
     * You should call {@code isValidLine(String)} method before
     * calling this method to avoid getting unexpected exceptions.
     *
     * @param line the {@code line} to be converted.
     * @return entity that is converted from the {@code line}.
     */
    T mapFrom(String line);
}
