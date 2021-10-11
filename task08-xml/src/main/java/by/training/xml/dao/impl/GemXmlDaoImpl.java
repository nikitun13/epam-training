package by.training.xml.dao.impl;

import by.training.xml.dao.DaoException;
import by.training.xml.dao.XmlDao;
import by.training.xml.dao.parser.GemsBuilder;
import by.training.xml.dao.parser.GemsBuilderProvider;
import by.training.xml.entity.Gem;

import java.util.List;

/**
 * The class {@code GemXmlDaoImpl} is a class
 * that implements {@link XmlDao}.
 *
 * @author Nikita Romanov
 * @see XmlDao
 */
public class GemXmlDaoImpl implements XmlDao<Gem> {

    private final GemsBuilderProvider provider;

    public GemXmlDaoImpl() {
        provider = GemsBuilderProvider.getInstance();
    }

    @Override
    public List<Gem> loadEntities(final String pathToXml,
                                  final String parserName)
            throws DaoException {
        GemsBuilder builder = provider.getGemsBuilder(parserName)
                .orElseThrow(
                        () -> new DaoException("unknown parser: " + parserName)
                );
        return builder.build(pathToXml);
    }
}
