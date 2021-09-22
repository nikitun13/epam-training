package by.training.shapes.dao.specification;

import by.training.shapes.entity.Ball;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

/**
 * The class {@code FindByXIsGreaterThenBallSpecification}
 * is a class that implements {@link Specification}.<br/>
 * Checks if ball's {@code x} center {@code Point} is greater than {@code value}
 *
 * @author Nikita Romanov
 * @see Specification
 */
public class FindByXIsGreaterThenBallSpecification
        implements Specification<Ball> {

    /**
     * Value for filtering by {@code x}.
     */
    private double value;

    public FindByXIsGreaterThenBallSpecification(final double value) {
        this.value = value;
    }

    @Override
    public boolean isSpecified(final Ball entity) {
        double x = entity.getCenterPoint().getX();
        return x > value;
    }

    @Override
    public Optional<Comparator<Ball>> getOptionalComparator() {
        return Optional.empty();
    }

    public double getValue() {
        return value;
    }

    public void setValue(final double value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FindByXIsGreaterThenBallSpecification that
                = (FindByXIsGreaterThenBallSpecification) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "FindByXIsGreaterThenBallSpecification{"
                + "value=" + value
                + '}';
    }
}
