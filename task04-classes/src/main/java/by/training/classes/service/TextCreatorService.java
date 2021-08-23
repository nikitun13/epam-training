package by.training.classes.service;

import by.training.classes.entity.Text;

/**
 * Describes the interface of a service
 * that creates {@link Text}.
 *
 * @author Nikita Romanov
 * @see Text
 */
public interface TextCreatorService {

    /**
     * Creates {@link Text} using
     * string value of this {@code text}.
     * Header must be seperated from body by one empty line.
     *
     * @param value string value of the {@link Text}.
     * @return new {@code Text} from {@code value}.
     * @throws ServiceException if {@code value} is null
     *                          or blank.
     */
    Text createText(String value) throws ServiceException;
}
