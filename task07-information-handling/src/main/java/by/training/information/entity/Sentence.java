package by.training.information.entity;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * The class {@code Sentence} is an entity class
 * that extends {@link AbstractTextComponent} and
 * represents a sentence entity.
 *
 * @author Nikita Romvanov
 * @see AbstractTextComponent
 */
public class Sentence extends AbstractTextComponent {

    public Sentence(final List<? extends TextComponent> childrenComponents) {
        super(childrenComponents);
    }

    @Override
    public String collect() {
        final String lexemeDelimiter = " ";
        return childrenComponents.stream()
                .map(TextComponent::collect)
                .collect(joining(lexemeDelimiter));
    }
}
