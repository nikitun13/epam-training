package by.training.xml.dao;

import java.util.List;

/**
 * Describes the interface of a dao that provides
 * access to entities from the XML file.
 *
 * @param <T> type of the entity.
 * @author Nikita Romanov
 */
public interface XmlDao<T> {

    /**
     * Loads all entities from the XML file.
     *
     * @param pathToXml  path to XML with entities.
     * @param parserName parser name (dom, sax or stax).
     * @return list of entities from the XML.
     * @throws DaoException if XML file or path is invalid.
     */
    List<T> loadEntities(String pathToXml, String parserName)
            throws DaoException;
}
