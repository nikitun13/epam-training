package by.training.information.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The class {@code Symbol} is an entity class
 * that implements {@link TextComponent} and
 * represents a symbol entity. <br/>
 * This class is a leaf in a composite hierarchy.
 *
 * @author Nikita Romvanov
 */
public final class Symbol implements TextComponent {

    /**
     * Cache of {@code Symbol} values.
     */
    private static final Map<Character, Symbol> SYMBOL_CACHE;

    static {
        SYMBOL_CACHE = new HashMap<>();
    }

    /**
     * Char value of the {@code Symbol}.
     */
    private final char value;

    private Symbol(final char value) {
        this.value = value;
    }

    @Override
    public String collect() {
        return String.valueOf(value);
    }

    /**
     * Returns {@code Symbol} representation of {@code character}.
     *
     * @param character character to be converted to {@code Symbol}.
     * @return {@code Symbol} representation of {@code character}.
     */
    public static Symbol valueOf(final char character) {
        if (SYMBOL_CACHE.containsKey(character)) {
            return SYMBOL_CACHE.get(character);
        } else {
            Symbol symbol = new Symbol(character);
            SYMBOL_CACHE.put(character, symbol);
            return symbol;
        }
    }

    /**
     * Getter.
     *
     * @return {@code Symbol} char value.
     */
    public char getValue() {
        return value;
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
