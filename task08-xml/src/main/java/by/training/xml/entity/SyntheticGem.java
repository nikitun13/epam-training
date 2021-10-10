package by.training.xml.entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The class {@code SyntheticGem} is an entity class
 * that extends {@link Gem} and
 * represents synthetic gemstone entity.
 *
 * @author Nikita Romanov
 */
public class SyntheticGem extends Gem {

    private String laboratory;

    public SyntheticGem() {
    }

    public SyntheticGem(final long id, final String name,
                        final Preciousness preciousness, final String origin,
                        final VisualParameters parameters, final double value,
                        final LocalDate date, final String laboratory) {
        super(id, name, preciousness, origin, parameters, value, date);
        this.laboratory = laboratory;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(final String laboratory) {
        this.laboratory = laboratory;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SyntheticGem that = (SyntheticGem) o;
        return Objects.equals(laboratory, that.laboratory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), laboratory);
    }

    @Override
    public String toString() {
        return "SyntheticGem{"
                + "id=" + getId()
                + ", name='" + getName() + '\''
                + ", preciousness=" + getPreciousness()
                + ", origin='" + getOrigin() + '\''
                + ", parameters=" + getParameters()
                + ", value=" + getValue()
                + ", date=" + getDate()
                + ", laboratory='" + laboratory + '\''
                + '}';
    }
}
