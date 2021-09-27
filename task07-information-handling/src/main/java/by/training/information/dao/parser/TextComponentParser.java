package by.training.information.dao.parser;

import by.training.information.entity.TextComponent;

import java.util.List;

/**
 * Describes interface of a parser that parses text.
 *
 * @author Nikita Romanov
 */
@FunctionalInterface
public interface TextComponentParser {

    /**
     * Parses {@code input} string.<br/>
     * Parsing result depends on the concrete {@code Parser}.
     *
     * @param input string to be parsed.
     * @return parsed list of {@code text components}.
     * @see TextComponent
     */
    List<? extends TextComponent> parse(String input);
}
