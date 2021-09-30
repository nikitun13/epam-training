package by.training.information.dao.parser;

import by.training.information.entity.TextComponent;
import by.training.information.entity.TextComponent.Type;
import by.training.information.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * The class {@code StringToLexemesParser} is a class
 * that extends {@link AbstractChainParser}.<br/>
 * Parses input string to {@code Lexemes}.
 *
 * @author Nikita Romanov
 * @see StringToTextComponentsParser
 * @see TextComposite
 */
public class StringToLexemesParser extends AbstractChainParser {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(StringToLexemesParser.class);

    /**
     * Regular expression for splitting input string into lexemes.
     */
    private static final String LEXEME_DELIMITER_REGEX = "\\s+";

    @Override
    public List<TextComponent> parse(final String input) {
        log.debug("received input for StringToLexemesParser: {}", input);
        String[] splitLexemes = input.split(LEXEME_DELIMITER_REGEX);
        List<TextComponent> lexemes = Arrays.stream(splitLexemes)
                .map(nextParser::parse)
                .map(childComponents -> new TextComposite(
                        Type.LEXEME, childComponents))
                .map(TextComponent.class::cast)
                .toList();
        log.debug("result lexemes: {}", lexemes);
        return lexemes;
    }
}
