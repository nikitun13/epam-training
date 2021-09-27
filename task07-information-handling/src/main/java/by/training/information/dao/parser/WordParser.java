package by.training.information.dao.parser;

import by.training.information.entity.TextComponent;
import by.training.information.entity.Word;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class {@code WordParser} is a class
 * that extends {@link AbstractChainParser}.<br/>
 * Parses input string to {@code Words}.
 *
 * @author Nikita Romanov
 * @see TextComponentParser
 * @see Word
 */
public class WordParser extends AbstractChainParser {

    /**
     * Logger.
     */
    private static final Logger log = LogManager.getLogger(WordParser.class);

    /**
     * Pattern for words from input.
     */
    private static final Pattern WORD_PATTERN = Pattern.compile("[\\p{Alpha}а-яА-ЯёЁ]+");

    @Override
    public List<? extends TextComponent> parse(final String input) {
        log.debug("received input for WordParser: {}", input);
        Matcher matcher = WORD_PATTERN.matcher(input);
        List<String> wordsFromInput = new ArrayList<>();
        while (matcher.find()) {
            String word = matcher.group();
            log.debug("match word: {}", word);
            wordsFromInput.add(word);
        }
        List<Word> words = wordsFromInput.stream()
                .map(nextParser::parse)
                .map(Word::new)
                .toList();
        log.debug("result words: {}", words);
        return words;
    }
}
