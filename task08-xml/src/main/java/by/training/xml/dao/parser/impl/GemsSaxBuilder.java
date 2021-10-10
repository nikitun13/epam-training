package by.training.xml.dao.parser.impl;

import by.training.xml.dao.DaoException;
import by.training.xml.dao.parser.*;
import by.training.xml.entity.Gem;
import by.training.xml.entity.Gem.Preciousness;
import by.training.xml.entity.SyntheticGem;
import by.training.xml.entity.VisualParameters;
import by.training.xml.entity.VisualParameters.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * The class {@code GemsSaxBuilder}
 * is a class that implements {@link GemsBuilder}.
 *
 * @author Nikita Romanov
 * @see GemsBuilder
 */
public class GemsSaxBuilder implements GemsBuilder {

    private static final Logger log
            = LogManager.getLogger(GemsSaxBuilder.class);

    private final GemsXmlValidator validator
            = GemsXmlValidator.getInstance();
    private final SAXParser parser;

    public GemsSaxBuilder() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SchemaProvider schemaProvider
                    = SchemaProvider.getInstance();
            Schema schema = schemaProvider.getSchema(SchemaProvider.GEMS_NAME)
                    .orElseThrow(LoadSchemaException::new);
            factory.setNamespaceAware(true);
            factory.setSchema(schema);
            factory.setFeature(
                    "http://apache.org/xml/features/disallow-doctype-decl",
                    true
            );
            factory.setValidating(false);
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException
                | SAXException e) {
            log.fatal(e);
            throw new CreationBuilderException(e);
        }
    }

    @Override
    public List<Gem> build(final String path) throws DaoException {
        log.debug("received: {}", path);
        if (!validator.isValid(path)) {
            throw new DaoException("xml or path to xml is invalid");
        }
        try {
            GemsContentHandler handler = new GemsContentHandler();
            parser.parse(
                    getClass().getClassLoader().getResourceAsStream(path),
                    handler
            );
            List<Gem> result = handler.getResult();
            log.debug("result: {}", result);
            return result;
        } catch (IOException | SAXException e) {
            throw new DaoException(e);
        }
    }

    private static final class GemsContentHandler extends DefaultHandler {

        private static final String VALUE_KEY = "value";

        private final List<Gem> result;
        private final EnumSet<GemsXmlTag> withText;
        private Gem currentGem;
        private VisualParameters currentParameters;
        private GemsXmlTag currentTag;

        private GemsContentHandler() {
            result = new ArrayList<>();
            withText = EnumSet.range(GemsXmlTag.NAME, GemsXmlTag.DATE);
        }

        public List<Gem> getResult() {
            return result;
        }

        @Override
        public void startElement(final String uri,
                                 final String localName,
                                 final String qName,
                                 final Attributes attributes)
                throws SAXException {
            log.debug("start element: {}", qName);
            GemsXmlTag tag
                    = GemsXmlTag.valueOf(qName.toUpperCase().replace("-", "_"));
            if (tag == GemsXmlTag.GEM || tag == GemsXmlTag.SYNTHETIC_GEM) {
                if (tag == GemsXmlTag.GEM) {
                    currentGem = new Gem();
                } else {
                    currentGem = new SyntheticGem();
                }
                currentParameters = new VisualParameters();
                currentGem.setId(Long.parseLong(
                        attributes.getValue("id").replace("x", "")
                ));
            } else {
                if (withText.contains(tag)) {
                    currentTag = tag;
                } else {
                    if (tag == GemsXmlTag.TRANSPARENCY) {
                        currentParameters.setTransparency(
                                Integer.parseInt(attributes.getValue(VALUE_KEY))
                        );
                    } else if (tag == GemsXmlTag.FACETS_NUMBER) {
                        currentParameters.setFacetsNumber(
                                Integer.parseInt(attributes.getValue(VALUE_KEY))
                        );
                    }
                }
            }
        }

        @Override
        public void endElement(final String uri, final String localName,
                               final String qName) throws SAXException {
            log.debug("end element: {}", qName);
            GemsXmlTag tag
                    = GemsXmlTag.valueOf(qName.toUpperCase().replace("-", "_"));
            if (tag == GemsXmlTag.GEM || tag == GemsXmlTag.SYNTHETIC_GEM) {
                currentGem.setParameters(currentParameters);
                log.debug("built gem: {}", currentGem);
                result.add(currentGem);
            }
        }

        @Override
        public void characters(final char[] ch, final int start,
                               final int length) {
            if (currentTag != null) {
                String data = new String(ch, start, length).strip();
                log.debug("got text: {}", data);
                switch (currentTag) {
                    case NAME -> currentGem.setName(data);
                    case PRECIOUSNESS -> currentGem.setPreciousness(
                            Preciousness.valueOf(data.toUpperCase())
                    );
                    case ORIGIN -> currentGem.setOrigin(data);
                    case VALUE -> currentGem.setValue(Double.parseDouble(data));
                    case DATE -> currentGem.setDate(LocalDate.parse(data));
                    case COLOR -> currentParameters.setColor(
                            Color.valueOf(data.toUpperCase())
                    );
                    case LABORATORY -> ((SyntheticGem) currentGem).setLaboratory(data);
                    default -> throw new EnumConstantNotPresentException(
                            currentTag.getDeclaringClass(), currentTag.name()
                    );
                }
                currentTag = null;
            }
        }

        @Override
        public void warning(final SAXParseException e) {
            log.warn(e);
        }

        @Override
        public void error(final SAXParseException e) throws SAXException {
            log.error(e);
        }

        @Override
        public void fatalError(final SAXParseException e) throws SAXException {
            log.fatal(e);
        }

        private enum GemsXmlTag {
            GEMS("gems"),
            GEM("gem"),
            SYNTHETIC_GEM("synthetic-gem"),
            NAME("name"),
            PRECIOUSNESS("preciousness"),
            ORIGIN("origin"),
            COLOR("color"),
            VALUE(VALUE_KEY),
            LABORATORY("laboratory"),
            DATE("date"),
            VISUAL_PARAMETERS("visual-parameters"),
            TRANSPARENCY("transparency"),
            FACETS_NUMBER("facets-number");

            private final String value;

            GemsXmlTag(final String value) {
                this.value = value;
            }

            public String getValue() {
                return value;
            }
        }
    }
}
