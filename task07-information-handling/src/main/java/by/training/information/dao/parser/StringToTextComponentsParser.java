package by.training.information.dao.parser;

import by.training.information.entity.TextComponent;

import java.util.List;

/**
 * Describes interface of a parser that parses
 * string to {@code TextComponents}.
 *
 * @author Nikita Romanov
 * @see TextComponent
 */
@FunctionalInterface
public interface StringToTextComponentsParser {

    /**
     * Parses {@code input} string to {@code TextComponents}.<br/>
     * Parsing result depends on the concrete {@code Parser}.
     *
     * @param input string to be parsed.
     * @return parsed list of {@code TextComponents}.
     * @see TextComponent
     */
    List<TextComponent> parse(String input);
}
