package by.training.xml.dao.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import java.io.IOException;

/**
 * The class {@code GemsXmlValidator} is a
 * validator that validates Gems XML.
 *
 * @author Nikita Romanov
 */
public final class GemsXmlValidator {

    private static final Logger log
            = LogManager.getLogger(GemsXmlValidator.class);
    private static final GemsXmlValidator INSTANCE
            = new GemsXmlValidator();

    private final Validator validator;

    private GemsXmlValidator() {
        Schema schema = SchemaProvider.getInstance()
                .getSchema(SchemaProvider.GEMS_NAME)
                .orElseThrow(LoadSchemaException::new);
        validator = schema.newValidator();
    }

    /**
     * Validates Gems XML.
     *
     * @param path path to XML.
     * @return {@code true} if XML is valid,
     * {@code false} otherwise.
     */
    public boolean isValid(final String path) {
        try {
            Source xmlFile = new StreamSource(
                    getClass().getClassLoader()
                            .getResourceAsStream(path)
            );
            validator.validate(xmlFile);
            log.debug("xml {} is valid", path);
            return true;
        } catch (SAXException | IOException e) {
            log.debug("xml {} is invalid: ", path, e);
            return false;
        }
    }

    public static GemsXmlValidator getInstance() {
        return INSTANCE;
    }
}
