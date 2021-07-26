package by.epam.linear.entity;

import java.util.Objects;

public class Resistor {

    private double resistance;

    public Resistor(double resistance) {
        this.resistance = resistance;
    }

    public double getResistance() {
        return resistance;
    }

    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resistor resistor = (Resistor) o;
        return Double.compare(resistor.resistance, resistance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resistance);
    }

    @Override
    public String toString() {
        return "Resistor{" +
                "resistance=" + resistance +
                '}';
    }
}
