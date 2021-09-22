package by.training.shapes.dao.specification;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

/**
 * The class {@code AndSpecification}
 * is a class that implements {@link Specification}.<br/>
 * Represents logical 'And' for other specifications.
 *
 * @param <T> type of entity of specification.
 * @author Nikita Romanov
 * @see Specification
 */
public class AndSpecification<T> implements Specification<T> {

    /**
     * First specification for 'And' union.
     */
    private Specification<T> firstSpecification;
    /**
     * Second specification for 'And' union.
     */
    private Specification<T> secondSpecification;

    public AndSpecification(final Specification<T> firstSpecification,
                            final Specification<T> secondSpecification) {
        this.firstSpecification = firstSpecification;
        this.secondSpecification = secondSpecification;
    }

    @Override
    public boolean isSpecified(final T entity) {
        return firstSpecification.isSpecified(entity)
                && secondSpecification.isSpecified(entity);
    }

    @Override
    public Optional<Comparator<T>> getOptionalComparator() {
        Optional<Comparator<T>> optionalFirstComparator
                = firstSpecification.getOptionalComparator();
        Optional<Comparator<T>> optionalSecondComparator
                = secondSpecification.getOptionalComparator();
        if (optionalFirstComparator.isPresent()
                && optionalSecondComparator.isPresent()) {
            Comparator<T> firstComparator = optionalFirstComparator.get();
            Comparator<T> secondComparator = optionalSecondComparator.get();
            return Optional.of(firstComparator.thenComparing(secondComparator));
        } else if (optionalFirstComparator.isPresent()) {
            return optionalFirstComparator;
        } else {
            return optionalSecondComparator;
        }
    }

    public Specification<T> getFirstSpecification() {
        return firstSpecification;
    }

    public void setFirstSpecification(
            final Specification<T> firstSpecification) {
        this.firstSpecification = firstSpecification;
    }

    public Specification<T> getSecondSpecification() {
        return secondSpecification;
    }

    public void setSecondSpecification(
            final Specification<T> secondSpecification) {
        this.secondSpecification = secondSpecification;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AndSpecification<?> that = (AndSpecification<?>) o;
        return Objects.equals(firstSpecification, that.firstSpecification)
                && Objects.equals(secondSpecification, that.secondSpecification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstSpecification, secondSpecification);
    }

    @Override
    public String toString() {
        return "AndSpecification{"
                + "firstSpecification=" + firstSpecification
                + ", secondSpecification=" + secondSpecification
                + '}';
    }
}
