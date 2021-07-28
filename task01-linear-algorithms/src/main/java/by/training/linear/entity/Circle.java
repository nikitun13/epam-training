package by.training.linear.entity;

import java.util.Objects;

public class Circle {

    private double circumference;

    public Circle(double circumference) {
        this.circumference = circumference;
    }

    public double getCircumference() {
        return circumference;
    }

    public void setCircumference(double circumference) {
        this.circumference = circumference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return circumference == circle.circumference;
    }

    @Override
    public int hashCode() {
        return Objects.hash(circumference);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "circumference=" + circumference +
                '}';
    }
}
