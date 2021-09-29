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
 * The class {@code StringToExpressionParser} is a class
 * that extends {@link AbstractChainParser}.<br/>
 * Parses input string to {@code Expressions}.
 *
 * @author Nikita Romanov
 * @see StringToTextComponentsParser
 * @see TextComposite
 */
public class StringToExpressionParser extends AbstractChainParser {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(StringToExpressionParser.class);

    /**
     * Expression regex.
     */
    private static final String EXPRESSION_REGEX
            = "((\\(*~?\\d+([<]{2}|[>]{2,3}|[\\^&|]))+"
            + "(~?\\d+\\)*([<]{2}|[>]{2,3}|[\\^&|])?)+)";
    /**
     * Groups for other elements that doesn't
     * match expression pattern for further parsing.
     */
    private static final Pattern GROUPS_PATTERN
            = Pattern.compile(EXPRESSION_REGEX + "|(\\p{Graph}+)");

    @Override
    public List<TextComponent> parse(final String input) {
        log.debug("received input for StringToExpressionParser: {}", input);
        Matcher matcher = GROUPS_PATTERN.matcher(input);
        List<TextComponent> textComponents = new ArrayList<>();
        while (matcher.find()) {
            String group = matcher.group();
            log.debug("found group: {}", group);
            if (group.matches(EXPRESSION_REGEX)) {
                log.debug("parse as expression");
                List<TextComponent> parsedExpression = nextParser.parse(group);
                TextComposite expression = new TextComposite(Type.EXPRESSION, parsedExpression);
                textComponents.add(expression);
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
