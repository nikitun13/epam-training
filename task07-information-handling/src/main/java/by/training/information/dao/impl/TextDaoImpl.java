package by.training.information.dao.impl;

import by.training.information.dao.DaoException;
import by.training.information.dao.TextDao;
import by.training.information.dao.parser.*;
import by.training.information.dao.reader.Reader;
import by.training.information.dao.reader.ReaderImpl;
import by.training.information.entity.Text;
import by.training.information.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * The class {@code TextDaoImpl} is a class
 * that implements {@link TextDao}.
 *
 * @author Nikita Romanov
 * @see TextDao
 */
public class TextDaoImpl implements TextDao {

    /**
     * Logger.
     */
    private static final Logger log = LogManager.getLogger(TextDaoImpl.class);

    /**
     * Reader for reading data from the file.
     */
    private final Reader reader = ReaderImpl.getInstance();
    /**
     * Parser for parsing input text.
     */
    private final StringToTextComponentsParser parser;

    public TextDaoImpl() {
        AbstractChainParser headParser = new StringToParagraphsParser();
        headParser.setNextParser(new StringToSentencesParser())
                .setNextParser(new StringToLexemesParser())
                .setNextParser(new StringToWordParser())
                .setNextParser(new StringToExpressionParser());
        parser = headParser;
    }

    @Override
    public Text read(final Path path) throws DaoException {
        log.debug("received path: {}", path);
        final String delimiter = "\n";
        String unparsedText = String.join(delimiter, reader.readAllLines(path));
        List<TextComponent> textComponents = parser.parse(unparsedText);
        Text text = new Text(textComponents);
        log.debug("result text: {}", text);
        return text;
    }

    @Override
    public void write(final Path path, final Text text) throws DaoException {
        log.debug("received path: {}, text: {}", path, text);
        String collectedText = text.collect();
        log.debug("collected text: {}", collectedText);
        try {
            Files.writeString(path, collectedText);
            log.debug("text is written");
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }
}
