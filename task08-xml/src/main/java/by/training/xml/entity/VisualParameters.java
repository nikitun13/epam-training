package by.training.xml.entity;

import java.util.Objects;

/**
 * The class {@code VisualParameters} is an entity class
 * that represents visual parameters of gemstones.
 *
 * @author Nikita Romanov
 */
public class VisualParameters {

    private Color color;
    private int transparency;
    private int facetsNumber;

    public VisualParameters() {
    }

    public VisualParameters(Color color, int transparency, int facetsNumber) {
        this.color = color;
        this.transparency = transparency;
        this.facetsNumber = facetsNumber;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    public int getFacetsNumber() {
        return facetsNumber;
    }

    public void setFacetsNumber(int facetsNumber) {
        this.facetsNumber = facetsNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualParameters that = (VisualParameters) o;
        return transparency == that.transparency && facetsNumber == that.facetsNumber && color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, transparency, facetsNumber);
    }

    @Override
    public String toString() {
        return "VisualParameters{" +
                "color=" + color +
                ", transparency=" + transparency +
                ", facetsNumber=" + facetsNumber +
                '}';
    }

    public enum Color {

        RED, GREEN, WHITE, BLUE, YELLOW
    }
}
