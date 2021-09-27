package by.training.information.entity;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * The class {@code Paragraph} is an entity class
 * that extends {@link AbstractTextComponent} and
 * represents a paragraph entity.
 *
 * @author Nikita Romvanov
 * @see AbstractTextComponent
 */
public class Paragraph extends AbstractTextComponent {

    public Paragraph(final List<? extends TextComponent> childrenComponents) {
        super(childrenComponents);
    }

    @Override
    public String collect() {
        final String sentenceDelimiter = " ";
        final String tab = "    ";
        return tab + childrenComponents.stream()
                .map(TextComponent::collect)
                .collect(joining(sentenceDelimiter));
    }
}
