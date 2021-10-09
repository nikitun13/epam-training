package by.training.xml.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Gem {

    protected String name;
    protected Preciousness preciousness;
    protected String origin;
    protected VisualParameters parameters;
    protected double value;
    protected LocalDate date;

    public Gem() {
    }

    public Gem(String name, Preciousness preciousness, String origin, VisualParameters parameters, double value, LocalDate date) {
        this.name = name;
        this.preciousness = preciousness;
        this.origin = origin;
        this.parameters = parameters;
        this.value = value;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Preciousness getPreciousness() {
        return preciousness;
    }

    public void setPreciousness(Preciousness preciousness) {
        this.preciousness = preciousness;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public VisualParameters getParameters() {
        return parameters;
    }

    public void setParameters(VisualParameters parameters) {
        this.parameters = parameters;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gem gem = (Gem) o;
        return Double.compare(gem.value, value) == 0 && Objects.equals(name, gem.name) && preciousness == gem.preciousness && Objects.equals(origin, gem.origin) && Objects.equals(parameters, gem.parameters) && Objects.equals(date, gem.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, preciousness, origin, parameters, value, date);
    }

    @Override
    public String toString() {
        return "Gem{"
                + "name='" + name + '\''
                + ", preciousness=" + preciousness
                + ", origin='" + origin + '\''
                + ", parameters=" + parameters
                + ", value=" + value
                + ", date=" + date
                + '}';
    }
}
