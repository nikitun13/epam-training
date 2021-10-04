package by.training.information.entity;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

/**
 * The class {@code Symbol} is an entity class
 * that represents a Reverse Polish notation entity.
 *
 * @author Nikita Romvanov
 */
public class ReversePolishNotation {

    private final List<String> items;

    public ReversePolishNotation(final Deque<String> items) {
        this.items = new ArrayList<>(items);
    }

    public String pop() {
        return items.remove(size() - 1);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReversePolishNotation that = (ReversePolishNotation) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return "ReversePolishNotation{"
                + "items=" + items
                + '}';
    }
}
