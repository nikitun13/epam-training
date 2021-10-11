package by.training.xml.service;

import java.util.List;

/**
 * Describes the interface of a service
 * that provides access to entities in the XML.
 *
 * @param <T> type of the entity.
 * @author Nikita Romanov
 */
public interface XmlService<T> {

    /**
     * Finds all entities from XML file.
     *
     * @param pathToXml  path to XML with entities.
     * @param parserName parser name (dom, sax or stax).
     * @return list of entities from the XML.
     * @throws ServiceException if dao exception occurred
     *                          or {@code pathToXml}
     *                          or {@code parserName} is invalid.
     */
    List<T> findEntities(String pathToXml, String parserName)
            throws ServiceException;
}
