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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The class {@code GemsDomBuilder}
 * is a class that implements {@link GemsBuilder}.
 *
 * @author Nikita Romanov
 * @see GemsBuilder
 */
public class GemsDomBuilder implements GemsBuilder {

    private static final Logger log
            = LogManager.getLogger(GemsDomBuilder.class);
    private static final String VALUE_KEY = "value";

    private final DocumentBuilder documentBuilder;
    private final GemsXmlValidator validator
            = GemsXmlValidator.getInstance();

    public GemsDomBuilder() {
        try {
            DocumentBuilderFactory factory
                    = DocumentBuilderFactory.newInstance();
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
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
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
        List<Gem> gems = new ArrayList<>();
        Element root = buildDocument(path);
        NodeList rootChildNodes = root.getChildNodes();
        for (int i = 0; i < rootChildNodes.getLength(); i++) {
            Node gemElement = rootChildNodes.item(i);
            if (gemElement.getNodeType() == Node.ELEMENT_NODE) {
                Gem gem = buildGem((Element) gemElement);
                log.debug("built gem: {}", gem);
                gems.add(gem);
            }
        }
        log.debug("result: {}", gems);
        return gems;
    }

    private Element buildDocument(final String path) throws DaoException {
        try {

            Document document = documentBuilder.parse(
                    getClass().getClassLoader().getResourceAsStream(path)
            );
            return document.getDocumentElement();
        } catch (IOException | SAXException e) {
            throw new DaoException(e);
        }
    }

    private Gem buildGem(final Element gemElement) {
        long id = Long.parseLong(gemElement.getAttribute("id")
                .replace("x", "")
        );
        String name = getElementTextContent(gemElement, "name");
        Preciousness preciousness = Preciousness.valueOf(getElementTextContent(
                gemElement, "preciousness").toUpperCase()
        );
        String origin = getElementTextContent(gemElement, "origin");
        double value
                = Double.parseDouble(getElementTextContent(gemElement, VALUE_KEY));
        LocalDate date
                = LocalDate.parse(getElementTextContent(gemElement, "date"));

        Element parametersElement = (Element) gemElement
                .getElementsByTagName("visual-parameters").item(0);
        Color color = Color.valueOf(
                getElementTextContent(parametersElement, "color")
                        .toUpperCase()
        );
        Element transparencyElement = (Element) parametersElement
                .getElementsByTagName("transparency").item(0);
        Element facetsNumberElement = (Element) parametersElement
                .getElementsByTagName("facets-number").item(0);
        int transparency
                = Integer.parseInt(transparencyElement.getAttribute(VALUE_KEY));
        int facetsNumber
                = Integer.parseInt(facetsNumberElement.getAttribute(VALUE_KEY));
        VisualParameters parameters
                = new VisualParameters(color, transparency, facetsNumber);

        if (gemElement.getTagName().equals("synthetic-gem")) {
            String laboratory = getElementTextContent(gemElement, "laboratory");
            return new SyntheticGem(id, name, preciousness, origin,
                    parameters, value, date, laboratory
            );
        } else {
            return new Gem(id, name, preciousness, origin,
                    parameters, value, date
            );
        }
    }

    private static String getElementTextContent(final Element element,
                                                final String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
