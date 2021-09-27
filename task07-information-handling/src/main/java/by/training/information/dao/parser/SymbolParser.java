package by.training.information.dao.parser;

import by.training.information.entity.Symbol;
import by.training.information.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class {@code SymbolParser} is a class
 * that implements {@link TextComponentParser}.<br/>
 * Parses input string to {@code Symbols}.
 *
 * @author Nikita Romanov
 * @see TextComponentParser
 * @see Symbol
 */
public class SymbolParser implements TextComponentParser {

    /**
     * Logger.
     */
    private static final Logger log = LogManager.getLogger(SymbolParser.class);

    @Override
    public List<? extends TextComponent> parse(final String input) {
        log.debug("received input for SymbolParser: {}", input);
        List<Symbol> symbols = input.chars()
                .mapToObj(codePoint -> (char) codePoint)
                .map(Symbol::valueOf)
                .toList();
        log.debug("result symbols: {}", symbols);
        return symbols;
    }
}
