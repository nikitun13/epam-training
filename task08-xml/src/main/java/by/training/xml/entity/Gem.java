package by.training.xml.entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The class {@code Gem} is an entity class
 * that represents gemstone entity.
 *
 * @author Nikita Romanov
 */
public class Gem {

    private long id;
    private String name;
    private Preciousness preciousness;
    private String origin;
    private VisualParameters parameters;
    private double value;
    private LocalDate date;

    public Gem() {
    }

    public Gem(final long id, final String name,
               final Preciousness preciousness, final String origin,
               final VisualParameters parameters, final double value,
               final LocalDate date) {
        this.id = id;
        this.name = name;
        this.preciousness = preciousness;
        this.origin = origin;
        this.parameters = parameters;
        this.value = value;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Preciousness getPreciousness() {
        return preciousness;
    }

    public void setPreciousness(final Preciousness preciousness) {
        this.preciousness = preciousness;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public VisualParameters getParameters() {
        return parameters;
    }

    public void setParameters(final VisualParameters parameters) {
        this.parameters = parameters;
    }

    public double getValue() {
        return value;
    }

    public void setValue(final double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gem gem = (Gem) o;
        return id == gem.id
                && Double.compare(gem.value, value) == 0
                && Objects.equals(name, gem.name)
                && preciousness == gem.preciousness
                && Objects.equals(origin, gem.origin)
                && Objects.equals(parameters, gem.parameters)
                && Objects.equals(date, gem.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, preciousness, origin,
                parameters, value, date
        );
    }

    @Override
    public String toString() {
        return "Gem{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", preciousness=" + preciousness
                + ", origin='" + origin + '\''
                + ", parameters=" + parameters
                + ", value=" + value
                + ", date=" + date
                + '}';
    }

    public enum Preciousness {
        PRECIOUS, SEMIPRECIOUS
    }
}
