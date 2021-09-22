package by.training.shapes.dao.specification;

import by.training.shapes.entity.Ball;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

/**
 * The class {@code FindByRadiusBetweenBallSpecification}
 * is a class that implements {@link Specification}.<br/>
 * Checks if ball's radius is between {@code minRadius}
 * and {@code maxRadius} inclusively.
 *
 * @author Nikita Romanov
 * @see Specification
 */
public class FindByRadiusBetweenBallSpecification
        implements Specification<Ball> {

    /**
     * Min radius for filtering.
     */
    private double minRadius;
    /**
     * Max radius for filtering.
     */
    private double maxRadius;

    /**
     * All args constructor.
     *
     * @param minRadius min radius.
     * @param maxRadius max radius.
     */
    public FindByRadiusBetweenBallSpecification(final double minRadius,
                                                final double maxRadius) {
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
    }

    @Override
    public boolean isSpecified(final Ball entity) {
        double radius = entity.getRadius();
        return minRadius <= radius
                && radius <= maxRadius;
    }

    @Override
    public Optional<Comparator<Ball>> getOptionalComparator() {
        return Optional.empty();
    }

    public double getMinRadius() {
        return minRadius;
    }

    public void setMinRadius(final double minRadius) {
        this.minRadius = minRadius;
    }

    public double getMaxRadius() {
        return maxRadius;
    }

    public void setMaxRadius(final double maxRadius) {
        this.maxRadius = maxRadius;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FindByRadiusBetweenBallSpecification that
                = (FindByRadiusBetweenBallSpecification) o;
        return Double.compare(that.minRadius, minRadius) == 0
                && Double.compare(that.maxRadius, maxRadius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minRadius, maxRadius);
    }

    @Override
    public String toString() {
        return "FindByRadiusBetweenBallSpecification{"
                + "minRadius=" + minRadius
                + ", maxRadius=" + maxRadius
                + '}';
    }
}
