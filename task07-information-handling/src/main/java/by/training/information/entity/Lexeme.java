package by.training.information.entity;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * The class {@code Lexeme} is an entity class
 * that extends {@link AbstractTextComponent} and
 * represents a lexeme entity.
 *
 * @author Nikita Romvanov
 * @see AbstractTextComponent
 */
public class Lexeme extends AbstractTextComponent {

    public Lexeme(final List<? extends TextComponent> childrenComponents) {
        super(childrenComponents);
    }

    @Override
    public String collect() {
        return childrenComponents.stream()
                .map(TextComponent::collect)
                .collect(joining());
    }
}
