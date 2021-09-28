package by.training.information.entity;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * The class {@code Expression} is an entity class
 * that extends {@link AbstractTextComponent} and
 * represents an expression entity.
 *
 * @author Nikita Romvanov
 * @see AbstractTextComponent
 */
public class Expression extends AbstractTextComponent {

    public Expression(final List<? extends TextComponent> childrenComponents) {
        super(childrenComponents);
    }

    @Override
    public String collect() {
        return childrenComponents.stream()
                .map(TextComponent::collect)
                .collect(joining());
    }

    @Override
    public String toString() {
        return "Expression{"
                + "components=" + childrenComponents
                + '}';
    }
}
