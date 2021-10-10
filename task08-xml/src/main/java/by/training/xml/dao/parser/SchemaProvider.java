package by.training.xml.dao.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

/**
 * The class {@code SchemaProvider} is a class
 * that provides access to schemas by theirs names.
 *
 * @author Nikita Romanov
 */
public final class SchemaProvider {

    private static final Logger log
            = LogManager.getLogger(SchemaProvider.class);
    private static final SchemaProvider INSTANCE
            = new SchemaProvider();

    private static final String GEMS_SCHEMA_PATH = "data/gems.xsd";

    public static final String GEMS_NAME = "gems";

    private final Map<String, Schema> repository;

    private SchemaProvider() {
        try {
            SchemaFactory schemaFactory
                    = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);

            Schema gemsSchema = schemaFactory.newSchema(
                    getClass().getClassLoader().getResource(GEMS_SCHEMA_PATH)
            );

            repository = new HashMap<>();
            repository.put(GEMS_NAME, gemsSchema);
        } catch (SAXException e) {
            log.fatal(e);
            throw new LoadSchemaException(e);
        }
    }

    public Optional<Schema> getSchema(String name) {
        return Optional.ofNullable(repository.get(name));
    }

    public static SchemaProvider getInstance() {
        return INSTANCE;
    }
}
