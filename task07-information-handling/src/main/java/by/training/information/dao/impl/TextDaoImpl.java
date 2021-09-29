package by.training.information.dao.impl;

import by.training.information.dao.DaoException;
import by.training.information.dao.TextDao;
import by.training.information.dao.parser.*;
import by.training.information.dao.reader.Reader;
import by.training.information.dao.reader.ReaderImpl;
import by.training.information.entity.TextComponent;
import by.training.information.entity.TextComposite;
import by.training.information.entity.TextComposite.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public TextComposite read(final Path path) throws DaoException {
        log.debug("received path: {}", path);
        final String delimiter = "\n";
        String unparsedText = String.join(delimiter, reader.readAllLines(path));
        List<TextComponent> textComponents = parser.parse(unparsedText);
        TextComposite text = new TextComposite(Type.TEXT, textComponents);
        log.debug("result text: {}", text);
        return text;
    }
}
