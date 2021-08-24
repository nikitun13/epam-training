package by.training.classes.service;

import by.training.classes.entity.Sentence;
import by.training.classes.entity.Text;

/**
 * Describes the interface of a service
 * that provides editing operations on {@link Text}.
 *
 * @author Nikita Romanov
 * @see Text
 */
public interface TextEditorService {

    /**
     * Adds {@link Sentence} to the given {@code text} with
     * the given position of {@code index}.
     *
     * @param text     text to be edited.
     * @param index    index of position.
     * @param sentence sentence to be added to the {@code text}.
     * @throws ServiceException if {@code text} or {@code sentence}
     *                          is {@code null}
     *                          or {@code index} is invalid.
     */
    void addSentence(Text text,
                     int index,
                     Sentence sentence) throws ServiceException;

    /**
     * Removes {@link Sentence} from the given {@code text}
     * with the given position of {@code index}.
     *
     * @param text  text to be edited.
     * @param index index of position.
     * @return removed {@link Sentence}.
     * @throws ServiceException if {@code text} is {@code null}
     *                          or {@code index} is invalid.
     */
    Sentence removeSentence(Text text, int index) throws ServiceException;

    /**
     * Replaces old header with a new one.
     *
     * @param text   text to be edited.
     * @param header new {@code header}.
     * @throws ServiceException if {@code text} or {@code header}
     *                          is {@code null}.
     */
    void changeHeader(Text text, Sentence header) throws ServiceException;
}
