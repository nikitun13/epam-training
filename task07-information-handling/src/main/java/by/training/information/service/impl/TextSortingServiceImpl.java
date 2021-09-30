package by.training.information.service.impl;

import by.training.information.entity.Symbol;
import by.training.information.entity.TextComponent;
import by.training.information.entity.TextComponent.Type;
import by.training.information.entity.TextComposite;
import by.training.information.service.ServiceException;
import by.training.information.service.TextSortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;

/**
 * The class {@code TextSortingServiceImpl}
 * is a class that implements {@link TextSortingService}.
 *
 * @author Nikita Romanov
 * @see TextSortingService
 */
public class TextSortingServiceImpl implements TextSortingService {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(TextSortingServiceImpl.class);
    private static final String RECEIVED_COMPONENT_LOG_MESSAGE
            = "received component: {}";
    private static final String RESULT_LOG_MESSAGE
            = "result: {}";
    private static final String INVALID_TEXT_EXCEPTION_MESSAGE
            = "invalid text component";

    @Override
    public TextComponent sortParagraphsByNumberOfSentences(
            final TextComponent component)
            throws ServiceException {
        log.debug(RECEIVED_COMPONENT_LOG_MESSAGE, component);
        if (component == null || component.getType() != Type.TEXT) {
            throw new ServiceException(INVALID_TEXT_EXCEPTION_MESSAGE);
        }
        TextComponent newText = component.copyComponent();
        Comparator<TextComponent> paragraphComparator = comparingInt(
                paragraph -> paragraph.getChildrenComponents().size()
        );
        newText.getChildrenComponents().sort(paragraphComparator);
        log.debug(RESULT_LOG_MESSAGE, newText);
        return newText;
    }

    @Override
    public TextComponent sortWordsInSentenceByLength(
            final TextComponent component) throws ServiceException {
        log.debug(RECEIVED_COMPONENT_LOG_MESSAGE, component);
        if (component == null || component.getType() != Type.TEXT) {
            throw new ServiceException(INVALID_TEXT_EXCEPTION_MESSAGE);
        }
        TextComponent newText = component.copyComponent();
        Comparator<TextComponent> wordComparator = Comparator.comparingInt(
                lexeme -> lexeme.getChildrenComponents().stream()
                        .filter(textComponent ->
                                textComponent.getType() == Type.WORD)
                        .map(TextComponent::getChildrenComponents)
                        .mapToInt(List::size)
                        .sum()
        );
        for (TextComponent paragraph : newText.getChildrenComponents()) {
            for (TextComponent sentence : paragraph.getChildrenComponents()) {
                sentence.getChildrenComponents().sort(wordComparator);
            }
        }
        log.debug(RESULT_LOG_MESSAGE, newText);
        return newText;
    }

    @Override
    public TextComponent sortLexemesInTextByNumberOfGivenSymbolDescThenByAlphabet(
            final TextComponent component, final Symbol symbol)
            throws ServiceException {
        log.debug(RECEIVED_COMPONENT_LOG_MESSAGE, component);
        if (component == null || symbol == null || component.getType() != Type.TEXT) {
            throw new ServiceException(INVALID_TEXT_EXCEPTION_MESSAGE);
        }
        List<TextComponent> allLexemes = new ArrayList<>();
        for (TextComponent paragraph : component.getChildrenComponents()) {
            for (TextComponent sentence : paragraph.getChildrenComponents()) {
                allLexemes.addAll(
                        sentence.copyComponent().getChildrenComponents()
                );
            }
        }
        log.debug("collected lexemes: {}", allLexemes);
        Comparator<TextComponent> lexemeComparator = Comparator.comparingLong(
                        (TextComponent lexeme) ->
                                lexeme.getChildrenComponents().stream()
                                        .flatMap(lexemeChildComponent -> {
                                            List<TextComponent> childrenComponents
                                                    = lexemeChildComponent
                                                    .getChildrenComponents();
                                            if (lexemeChildComponent.getType()
                                                    == Type.SYMBOL) {
                                                return Stream.of(lexemeChildComponent);
                                            } else {
                                                return childrenComponents.stream();
                                            }
                                        })
                                        .map(Symbol.class::cast)
                                        .filter(lexemeSymbol ->
                                                lexemeSymbol.equals(symbol))
                                        .count()
                )
                .reversed()
                .thenComparing(TextComponent::collect);
        allLexemes.sort(lexemeComparator);
        log.debug("sorted lexemes: {}", allLexemes);
        TextComposite paragraph
                = new TextComposite(Type.PARAGRAPH, allLexemes);
        TextComposite resultText
                = new TextComposite(Type.TEXT, List.of(paragraph));
        log.debug(RESULT_LOG_MESSAGE, resultText);
        return resultText;
    }
}
