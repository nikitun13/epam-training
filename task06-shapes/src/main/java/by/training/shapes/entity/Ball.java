package by.training.shapes.entity;

import java.util.Objects;

/**
 * The class {@code Ball} is
 * an entity class that represents geometric
 * shape of ball in three dimensions.
 *
 * @author Nikita Romanov
 */
public class Ball implements VolumetricShape {

    /**
     * Unique identifier of a {@code Ball}.<br/>
     * Uniqueness is provided by developer.
     */
    private int id;

    /**
     * Center point of the {@code Ball}.
     */
    private Point centerPoint;

    /**
     * Radius of the {@code Ball}.
     */
    private double radius;

    /**
     * All args constructor.
     *
     * @param newId          id of the {@code Ball}.
     * @param newRadius      radius of the {@code Ball}.
     * @param newCenterPoint center point of the {@code Ball}.
     */
    public Ball(final int newId,
                final double newRadius,
                final Point newCenterPoint) {
        id = newId;
        centerPoint = newCenterPoint;
        radius = newRadius;
    }

    /**
     * All args constructor without {@code id}.
     *
     * @param newRadius      radius of the {@code Ball}.
     * @param newCenterPoint center point of the {@code Ball}.
     */
    public Ball(final double newRadius,
                final Point newCenterPoint) {
        centerPoint = newCenterPoint;
        radius = newRadius;
    }

    /**
     * Getter.
     *
     * @return {@code id} of the {@code ball}.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter.
     *
     * @param newId new value of the {@code id}.
     */
    public void setId(final int newId) {
        id = newId;
    }

    /**
     * Getter.
     *
     * @return point of the center.
     */
    public Point getCenterPoint() {
        return centerPoint;
    }

    /**
     * Setter.
     *
     * @param newCenter new value of the center {@code point}.
     */
    public void setCenterPoint(final Point newCenter) {
        centerPoint = newCenter;
    }

    /**
     * Getter.
     *
     * @return radius value of the {@code Ball}.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Setter.
     *
     * @param newRadius new value of the {@code radius}.
     */
    public void setRadius(final double newRadius) {
        radius = newRadius;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ball ball = (Ball) o;
        return id == ball.id
                && Double.compare(ball.radius, radius) == 0
                && Objects.equals(centerPoint, ball.centerPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, centerPoint, radius);
    }

    @Override
    public String toString() {
        return "Ball{"
                + "id=" + id
                + ", centerPoint=" + centerPoint
                + ", radius=" + radius
                + '}';
    }
}
