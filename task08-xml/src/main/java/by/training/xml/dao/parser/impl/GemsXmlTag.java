package by.training.xml.dao.parser.impl;

/**
 * Enumeration of XML tags for Gems.
 *
 * @author Nikita Romanov
 */
public enum GemsXmlTag {

    GEMS("gems"),
    GEM("gem"),
    SYNTHETIC_GEM("synthetic-gem"),
    NAME("name"),
    PRECIOUSNESS("preciousness"),
    ORIGIN("origin"),
    COLOR("color"),
    VALUE("value"),
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
