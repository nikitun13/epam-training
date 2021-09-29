package by.training.information.dao.parser;

import by.training.information.entity.TextComponent;
import by.training.information.entity.TextComposite;
import by.training.information.entity.TextComposite.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class {@code StringToWordParser} is a class
 * that extends {@link AbstractChainParser}.<br/>
 * Parses input string to {@code Words}.
 *
 * @author Nikita Romanov
 * @see StringToTextComponentsParser
 * @see TextComposite
 */
public class StringToWordParser extends AbstractChainParser {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(StringToWordParser.class);

    /**
     * Word regex.
     */
    private static final String WORD_REGEX = "([\\p{Alpha}а-яА-ЯёЁ]+)";
    /**
     * Groups for other elements that doesn't
     * match word pattern for further parsing.
     */
    private static final Pattern GROUPS_PATTERN
            = Pattern.compile(WORD_REGEX + "|([\\p{Digit}\\p{Punct}]+)");

    @Override
    public List<TextComponent> parse(final String input) {
        log.debug("received input for StringToWordParser: {}", input);
        Matcher matcher = GROUPS_PATTERN.matcher(input);
        List<TextComponent> textComponents = new ArrayList<>();
        while (matcher.find()) {
            String group = matcher.group();
            log.debug("found group: {}", group);
            if (group.matches(WORD_REGEX)) {
                log.debug("parse as word");
                List<TextComponent> parsedWord = nextParser.parse(group);
                TextComposite word = new TextComposite(Type.WORD, parsedWord);
                textComponents.add(word);
            } else {
                log.debug("parse as other TextComponent");
                List<TextComponent> parsedComponents = nextParser.parse(group);
                textComponents.addAll(parsedComponents);
            }
        }
        log.debug("result TextComponents: {}", textComponents);
        return textComponents;
    }
}
