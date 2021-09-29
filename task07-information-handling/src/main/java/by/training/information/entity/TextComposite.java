package by.training.information.entity;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class TextComposite implements TextComponent {

    /**
     * Type of the text composite.
     */
    private final Type type;
    /**
     * List of children components.
     */
    private final List<? extends TextComponent> childrenComponents;

    public TextComposite(final Type type,
                         final List<? extends TextComponent> childrenComponents) {
        this.type = type;
        this.childrenComponents = childrenComponents;
    }

    @Override
    public String collect() {
        return childrenComponents.stream()
                .map(TextComponent::collect)
                .collect(joining(type.childDelimiter, type.prefix, ""));
    }

    public Type getType() {
        return type;
    }

    public List<TextComponent> getChildrenComponents() {
        return List.copyOf(childrenComponents);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TextComposite that = (TextComposite) o;
        return type == that.type
                && Objects.equals(childrenComponents, that.childrenComponents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, childrenComponents);
    }

    @Override
    public String toString() {
        return "TextComposite{"
                + "type=" + type
                + ", childrenComponents=" + childrenComponents
                + '}';
    }

    public enum Type {

        TEXT("\n"),
        PARAGRAPH(" ", "    "),
        SENTENCE(" "),
        LEXEME(),
        WORD(),
        EXPRESSION();

        /**
         * Default delimiter or prefix.
         */
        private static final String DEFAULT_SEPARATOR = "";

        private final String childDelimiter;
        private final String prefix;

        Type() {
            this(DEFAULT_SEPARATOR, DEFAULT_SEPARATOR);
        }

        Type(final String childDelimiter) {
            this(childDelimiter, DEFAULT_SEPARATOR);
        }

        Type(final String childDelimiter, final String prefix) {
            this.childDelimiter = childDelimiter;
            this.prefix = prefix;
        }

        public String getChildDelimiter() {
            return childDelimiter;
        }

        public String getPrefix() {
            return prefix;
        }
    }
}
