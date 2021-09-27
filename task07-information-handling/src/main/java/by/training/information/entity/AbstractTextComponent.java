package by.training.information.entity;

import java.util.List;
import java.util.Objects;

/**
 * The class {@code AbstractTextComponent} is an abstract class
 * that implements {@link TextComponent}.<br/>
 * This class is used for composite design pattern.
 *
 * @author Nikita Romanov
 * @see TextComponent
 */
public abstract class AbstractTextComponent implements TextComponent {

    /**
     * List of children components.
     */
    protected List<? extends TextComponent> childrenComponents;

    protected AbstractTextComponent(
            final List<? extends TextComponent> childrenComponents) {
        this.childrenComponents = childrenComponents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTextComponent that = (AbstractTextComponent) o;
        return Objects.equals(childrenComponents, that.childrenComponents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(childrenComponents);
    }
}
