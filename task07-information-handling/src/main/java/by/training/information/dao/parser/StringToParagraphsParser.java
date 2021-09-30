package by.training.information.dao.parser;

import by.training.information.entity.TextComponent;
import by.training.information.entity.TextComponent.Type;
import by.training.information.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * The class {@code StringToParagraphsParser} is a class
 * that extends {@link AbstractChainParser}.<br/>
 * Parses input string to {@code Paragraphs}.
 *
 * @author Nikita Romanov
 * @see StringToTextComponentsParser
 * @see TextComposite
 */
public class StringToParagraphsParser extends AbstractChainParser {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(StringToParagraphsParser.class);

    /**
     * Regular expression for splitting input string into paragraphs.
     */
    private static final String PARAGRAPH_DELIMITER_REGEX = "\n";

    @Override
    public List<TextComponent> parse(final String input) {
        log.debug("received input for StringToParagraphsParser: {}", input);
        String[] splitParagraphs = input.split(PARAGRAPH_DELIMITER_REGEX);
        List<TextComponent> paragraphs = Arrays.stream(splitParagraphs)
                .map(String::strip)
                .map(nextParser::parse)
                .map(childComponents -> new TextComposite(
                        Type.PARAGRAPH, childComponents))
                .map(TextComponent.class::cast)
                .toList();
        log.debug("result paragraphs: {}", paragraphs);
        return paragraphs;
    }
}
