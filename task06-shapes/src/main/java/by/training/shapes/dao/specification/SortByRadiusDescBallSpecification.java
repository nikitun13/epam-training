package by.training.shapes.dao.specification;

import by.training.shapes.entity.Ball;

import java.util.Comparator;
import java.util.Optional;

/**
 * The class {@code SortByRadiusDescBallSpecification} is a class
 * that implements {@link Specification}.<br/>
 * Sorts {@link Ball} entities by their radius in descending order.
 *
 * @author Nikita Romanov
 * @see Specification
 */
public class SortByRadiusDescBallSpecification implements Specification<Ball> {

    @Override
    public boolean isSpecified(final Ball entity) {
        return true;
    }

    @Override
    public Optional<Comparator<Ball>> getOptionalComparator() {
        return Optional.of(Comparator.comparing(Ball::getRadius).reversed());
    }

    @Override
    public String toString() {
        return "SortByRadiusDescBallSpecification{}";
    }
}
