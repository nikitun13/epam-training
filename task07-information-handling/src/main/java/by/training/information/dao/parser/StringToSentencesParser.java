package by.training.information.dao.parser;

import by.training.information.entity.Sentence;
import by.training.information.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class {@code StringToSentencesParser} is a class
 * that extends {@link AbstractChainParser}.<br/>
 * Parses input string into {@code Sentences}.
 *
 * @author Nikita Romanov
 * @see AbstractChainParser
 * @see Sentence
 */
public class StringToSentencesParser extends AbstractChainParser {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(StringToSentencesParser.class);

    /**
     * Pattern for words from input.
     */
    private static final Pattern SENTENCE_PATTERN
            = Pattern.compile("[A-ZА-ЯЁ].*?([.]{3}|[.]|[?!]{1,2})");

    @Override
    public List<TextComponent> parse(final String input) {
        log.debug("received input for StringToSentencesParser: {}", input);
        Matcher matcher = SENTENCE_PATTERN.matcher(input);
        List<String> stringsSentences = new ArrayList<>();
        while (matcher.find()) {
            String stringSentence = matcher.group();
            log.debug("find sentence: {}", stringSentence);
            stringsSentences.add(stringSentence);
        }
        List<TextComponent> sentences = stringsSentences.stream()
                .map(nextParser::parse)
                .map(Sentence::new)
                .map(TextComponent.class::cast)
                .toList();
        log.debug("result sentences: {}", sentences);
        return sentences;
    }
}
