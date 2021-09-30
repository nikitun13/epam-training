package by.training.information.entity;

import java.util.ArrayList;
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
    private final List<TextComponent> childrenComponents;

    @SuppressWarnings("unchecked")
    public TextComposite(final Type type,
                         final List<? extends TextComponent> childrenComponents) {
        this.type = type;
        this.childrenComponents = (List<TextComponent>) childrenComponents;
    }

    @Override
    public String collect() {
        String delimiter = type.getChildDelimiter();
        String prefix = type.getPrefix();
        String suffix = "";
        return childrenComponents.stream()
                .map(TextComponent::collect)
                .collect(joining(delimiter, prefix, suffix));
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public TextComponent copyComponent() {
        List<TextComponent> newChildrenComponents = new ArrayList<>();
        for (TextComponent component : childrenComponents) {
            newChildrenComponents.add(component.copyComponent());
        }
        return new TextComposite(type, newChildrenComponents);
    }

    @Override
    public List<TextComponent> getChildrenComponents() {
        return childrenComponents;
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
}
