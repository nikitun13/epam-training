package by.training.xml.dao.parser;

import by.training.xml.dao.DaoException;
import by.training.xml.entity.Gem;

import java.util.List;

/**
 * Describes interface of a {@link Gem} builder
 * from XML file.
 *
 * @author Nikita Romanov
 */
public interface GemsBuilder {

    /**
     * Builds {@code gems} (or its inheritors) from XML.
     *
     * @param path path to XML file.
     * @return list of {@code gems}.
     * @throws DaoException if {@code path} is invalid
     *                      or XML is invalid.
     */
    List<Gem> build(String path) throws DaoException;
}
