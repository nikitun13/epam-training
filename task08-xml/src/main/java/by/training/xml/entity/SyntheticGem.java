package by.training.xml.entity;

import java.time.LocalDate;
import java.util.Objects;

public class SyntheticGem extends Gem {

    private String laboratory;

    public SyntheticGem() {
    }

    public SyntheticGem(String name, Preciousness preciousness, String origin, VisualParameters parameters, double value, LocalDate date, String laboratory) {
        super(name, preciousness, origin, parameters, value, date);
        this.laboratory = laboratory;
    }

    @Override
    public boolean equals(Object o) {
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
                + "name='" + name + '\''
                + ", preciousness=" + preciousness
                + ", origin='" + origin + '\''
                + ", parameters=" + parameters
                + ", value=" + value
                + ", date=" + date
                + ", laboratory='" + laboratory + '\''
                + '}';
    }
}
