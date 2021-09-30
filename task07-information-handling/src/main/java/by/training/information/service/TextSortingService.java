package by.training.information.service;

import by.training.information.entity.Symbol;
import by.training.information.entity.TextComponent;

/**
 * Describes the interface of a service that
 * sorts {@link TextComponent}.
 *
 * @author Nikita Romanov
 * @see TextComponent
 */
public interface TextSortingService {

    /**
     * Sorts paragraphs in the {@link TextComponent} by number of sentences.
     *
     * @param component text component to be sorted.
     * @return sorted text component.
     * @throws ServiceException if {@code component} is invalid.
     */
    TextComponent sortParagraphsByNumberOfSentences(TextComponent component)
            throws ServiceException;

    /**
     * Sorts words in the sentence by word's length.
     *
     * @param component text component to be sorted.
     * @return sorted text component.
     * @throws ServiceException if {@code component} is invalid.
     */
    TextComponent sortWordsInSentenceByLength(TextComponent component)
            throws ServiceException;

    /**
     * Sorts all lexemes in the text by number of given {@code symbol} inside
     * in descending order, then sorts by alphabet.
     *
     * @param component text component to be sorted.
     * @param symbol    symbol for sorting.
     * @return sorted text component.
     * @throws ServiceException if {@code component} is invalid.
     */
    TextComponent sortLexemesInTextByNumberOfGivenSymbolDescThenByAlphabet(
            TextComponent component, Symbol symbol) throws ServiceException;
}
