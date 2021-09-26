package by.training.information.entity;

import java.util.Objects;

/**
 * The class {@code Symbol} is an entity class
 * that implements {@link TextComponent} and
 * represents a symbol entity. <br/>
 * This class is a leaf in a composite hierarchy.
 *
 * @author Nikita Romvanov
 */
public class Symbol implements TextComponent {

    /**
     * Char value of the symbol.
     */
    private final char value;

    public Symbol(final char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String collect() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Symbol symbol = (Symbol) o;
        return value == symbol.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Symbol{"
                + "value=" + value
                + '}';
    }
}
