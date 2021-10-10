package by.training.xml.dao.parser.impl;

import by.training.xml.dao.DaoException;
import by.training.xml.dao.parser.GemsBuilder;
import by.training.xml.dao.parser.GemsXmlValidator;
import by.training.xml.entity.Gem;
import by.training.xml.entity.Gem.Preciousness;
import by.training.xml.entity.SyntheticGem;
import by.training.xml.entity.VisualParameters;
import by.training.xml.entity.VisualParameters.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The class {@code GemsStaxBuilder}
 * is a class that implements {@link GemsBuilder}.
 *
 * @author Nikita Romanov
 * @see GemsBuilder
 */
public class GemsStaxBuilder implements GemsBuilder {

    private static final Logger log
            = LogManager.getLogger(GemsStaxBuilder.class);
    private static final String UNKNOWN_ELEMENT_MESSAGE = "unknown element";
    private static final int DEFAULT_TRANSPARENCY = 50;

    private final GemsXmlValidator validator
            = GemsXmlValidator.getInstance();

    private final XMLInputFactory factory;

    public GemsStaxBuilder() {
        factory = XMLInputFactory.newInstance();
    }

    @Override
    public List<Gem> build(final String path) throws DaoException {
        log.debug("received: {}", path);
        if (!validator.isValid(path)) {
            throw new DaoException("xml or path to xml is invalid");
        }
        try {
            XMLStreamReader reader = factory.createXMLStreamReader(
                    getClass().getClassLoader().getResourceAsStream(path)
            );
            List<Gem> gems = new ArrayList<>();
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    GemsXmlTag tag = getTagByName(reader);
                    if (tag == GemsXmlTag.GEM
                            || tag == GemsXmlTag.SYNTHETIC_GEM) {
                        Gem gem = buildGem(reader,
                                tag == GemsXmlTag.SYNTHETIC_GEM
                        );
                        log.debug("built gem: {}", gem);
                        gems.add(gem);
                    }
                }
            }
            log.debug("result: {}", gems);
            return gems;
        } catch (XMLStreamException e) {
            throw new DaoException(e);
        }
    }

    private Gem buildGem(final XMLStreamReader reader, final boolean synthetic)
            throws XMLStreamException, DaoException {
        Gem gem;
        if (synthetic) {
            gem = new SyntheticGem();
        } else {
            gem = new Gem();
        }
        gem.setId(Long.parseLong(
                reader.getAttributeValue(null, "id").replace("x", ""))
        );
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                GemsXmlTag tag = getTagByName(reader);
                switch (tag) {
                    case NAME -> gem.setName(getElementText(reader));
                    case PRECIOUSNESS -> gem.setPreciousness(
                            Preciousness.valueOf(
                                    getElementText(reader).toUpperCase()
                            )
                    );
                    case ORIGIN -> gem.setOrigin(getElementText(reader));
                    case VISUAL_PARAMETERS -> gem.setParameters(
                            buildParameters(reader)
                    );
                    case VALUE -> gem.setValue(
                            Double.parseDouble(getElementText(reader))
                    );
                    case DATE -> gem.setDate(
                            LocalDate.parse(getElementText(reader))
                    );
                    case LABORATORY -> ((SyntheticGem) gem).setLaboratory(
                            getElementText(reader)
                    );
                    default -> throw new DaoException(UNKNOWN_ELEMENT_MESSAGE);
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                GemsXmlTag tag = getTagByName(reader);
                if (tag == GemsXmlTag.GEM || tag == GemsXmlTag.SYNTHETIC_GEM) {
                    return gem;
                }
            }
        }
        throw new DaoException(UNKNOWN_ELEMENT_MESSAGE);
    }

    private VisualParameters buildParameters(final XMLStreamReader reader)
            throws XMLStreamException, DaoException {
        VisualParameters parameters = new VisualParameters();
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                GemsXmlTag tag = getTagByName(reader);
                switch (tag) {
                    case COLOR -> parameters.setColor(
                            Color.valueOf(getElementText(reader).toUpperCase())
                    );
                    case TRANSPARENCY -> parameters.setTransparency(
                            retrieveTransparency(reader)
                    );
                    case FACETS_NUMBER -> parameters.setFacetsNumber(
                            Integer.parseInt(
                                    reader.getAttributeValue(null, "value")
                            )
                    );
                    default -> throw new DaoException(UNKNOWN_ELEMENT_MESSAGE);
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                GemsXmlTag tag = getTagByName(reader);
                if (tag == GemsXmlTag.VISUAL_PARAMETERS) {
                    return parameters;
                }
            }
        }
        throw new DaoException(UNKNOWN_ELEMENT_MESSAGE);
    }

    private int retrieveTransparency(final XMLStreamReader reader) {
        String value = reader.getAttributeValue(null, "value");
        if (value == null) {
            return DEFAULT_TRANSPARENCY;
        } else {
            return Integer.parseInt(value);
        }
    }

    private String getElementText(final XMLStreamReader reader)
            throws XMLStreamException, DaoException {
        if (reader.hasNext()) {
            reader.next();
            return reader.getText();
        }
        throw new DaoException("expected more elements");
    }

    private GemsXmlTag getTagByName(final XMLStreamReader reader) {
        return GemsXmlTag.valueOf(
                reader.getLocalName()
                        .replace("-", "_")
                        .toUpperCase()
        );
    }
}
