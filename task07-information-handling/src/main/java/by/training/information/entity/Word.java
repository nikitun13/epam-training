package by.training.information.entity;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * The class {@code Word} is an entity class
 * that extends {@link AbstractTextComponent} and
 * represents a word entity.
 *
 * @author Nikita Romvanov
 * @see AbstractTextComponent
 */
public class Word extends AbstractTextComponent {

    public Word(final List<? extends TextComponent> childrenComponents) {
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
        return "Word{"
                + "components=" + childrenComponents
                + '}';
    }
}
