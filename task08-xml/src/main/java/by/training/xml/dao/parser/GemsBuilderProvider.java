package by.training.xml.dao.parser;

import by.training.xml.dao.parser.impl.GemsDomBuilder;
import by.training.xml.dao.parser.impl.GemsSaxBuilder;
import by.training.xml.dao.parser.impl.GemsStaxBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The class {@code GemsBuilderProvider} is a class
 * that provides access to {@code GemsBuilders} by theirs names.
 *
 * @author Nikita Romanov
 */
public final class GemsBuilderProvider {

    public static final String STAX_BUILDER = "stax";
    public static final String SAX_BUILDER = "sax";
    public static final String DOM_BUILDER = "dom";

    private static final GemsBuilderProvider INSTANCE
            = new GemsBuilderProvider();

    private final Map<String, GemsBuilder> repository;

    private GemsBuilderProvider() {
        repository = new HashMap<>();
        repository.put(DOM_BUILDER, new GemsDomBuilder());
        repository.put(SAX_BUILDER, new GemsSaxBuilder());
        repository.put(STAX_BUILDER, new GemsStaxBuilder());
    }

    public Optional<GemsBuilder> getGemsBuilder(final String name) {
        return Optional.ofNullable(repository.get(name));
    }

    public static GemsBuilderProvider getInstance() {
        return INSTANCE;
    }
}
