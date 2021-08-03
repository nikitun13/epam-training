package by.training.branching.entity;

import java.util.Objects;

/**
 * The class {@code RomanInteger} is a class that represents a roman number.
 *
 * @author Nikita Romanov
 */
public class RomanInteger {

    private String value;

    public RomanInteger(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RomanInteger that = (RomanInteger) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "RomanInteger{" +
                "value='" + value + '\'' +
                '}';
    }
}
