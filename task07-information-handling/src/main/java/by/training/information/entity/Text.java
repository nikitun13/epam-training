package by.training.information.entity;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * The class {@code Text} is an entity class
 * that extends {@link AbstractTextComponent} and
 * represents a text entity.
 *
 * @author Nikita Romvanov
 * @see AbstractTextComponent
 */
public class Text extends AbstractTextComponent {

    public Text(final List<? extends TextComponent> childrenComponents) {
        super(childrenComponents);
    }

    @Override
    public String collect() {
        final String paragraphDelimiter = "\n";
        return childrenComponents.stream()
                .map(TextComponent::collect)
                .collect(joining(paragraphDelimiter));
    }
}
