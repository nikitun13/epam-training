package by.training.information.dao.parser;

import by.training.information.entity.Symbol;
import by.training.information.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class {@code StringToSymbolParser} is a class
 * that implements {@link StringToTextComponentsParser}.<br/>
 * Parses input string to {@code Symbols}.
 *
 * @author Nikita Romanov
 * @see StringToTextComponentsParser
 * @see Symbol
 */
public class StringToSymbolParser
        implements StringToTextComponentsParser {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(StringToSymbolParser.class);

    @Override
    public List<TextComponent> parse(final String input) {
        log.debug("received input for StringToSymbolParser: {}", input);
        List<TextComponent> symbols = input.chars()
                .mapToObj(codePoint -> (char) codePoint)
                .map(Symbol::valueOf)
                .map(TextComponent.class::cast)
                .toList();
        log.debug("result symbols: {}", symbols);
        return symbols;
    }
}
