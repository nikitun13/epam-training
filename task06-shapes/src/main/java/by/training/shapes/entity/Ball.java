package by.training.shapes.entity;

import java.util.Objects;

/**
 * The class {@code Ball} is
 * an entity class that represents geometric
 * shape of ball in three dimensions.
 *
 * @author Nikita Romanov
 */
public class Ball {

    /**
     * Unique identifier of a {@code Ball}.
     */
    private final int id;

    /**
     * Center point of the {@code Ball}.
     */
    private Point center;

    /**
     * Radius of the {@code Ball}.
     */
    private double radius;

    /**
     * All args constructor.
     *
     * @param newId     id of the {@code Ball}.
     * @param newRadius radius of the {@code Ball}.
     * @param newCenter center point of the {@code Ball}.
     */
    public Ball(final int newId,
                final double newRadius,
                final Point newCenter) {
        id = newId;
        center = newCenter;
        radius = newRadius;
    }

    /**
     * Getter.
     *
     * @return point of the center.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Setter.
     *
     * @param newCenter new value of the center {@code point}.
     */
    public void setCenter(final Point newCenter) {
        center = newCenter;
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
                && Objects.equals(center, ball.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, center, radius);
    }

    @Override
    public String toString() {
        return "Ball{"
                + "id=" + id
                + ", center=" + center
                + ", radius=" + radius
                + '}';
    }

    /**
     * The class {@code Point} is a class
     * that represents point in three-dimensional space.
     *
     * @author Nikita Romanov
     */
    public static class Point {

        /**
         * {@code x} coordinate.
         */
        private double x;

        /**
         * {@code y} coordinate.
         */
        private double y;

        /**
         * {@code z} coordinate.
         */
        private double z;

        /**
         * All args constructor.
         *
         * @param newX x value.
         * @param newY y value.
         * @param newZ z value.
         */
        public Point(final double newX,
                     final double newY,
                     final double newZ) {
            x = newX;
            y = newY;
            z = newZ;
        }

        /**
         * Getter.
         *
         * @return {@code x} value
         */
        public double getX() {
            return x;
        }

        /**
         * Setter.
         *
         * @param newX value to be set
         */
        public void setX(final double newX) {
            x = newX;
        }

        /**
         * Getter.
         *
         * @return {@code y} value
         */
        public double getY() {
            return y;
        }

        /**
         * Setter.
         *
         * @param newY value to be set
         */
        public void setY(final double newY) {
            y = newY;
        }

        /**
         * Getter.
         *
         * @return {@code z} value
         */
        public double getZ() {
            return z;
        }

        /**
         * Setter.
         *
         * @param newZ value to be set
         */
        public void setZ(final double newZ) {
            z = newZ;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || o.getClass() != getClass()) {
                return false;
            }
            Point point = (Point) o;
            return Double.compare(x, point.x) == 0
                    && Double.compare(y, point.y) == 0
                    && Double.compare(z, point.z) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        @Override
        public String toString() {
            return "Point{"
                    + "x=" + x
                    + ", y=" + y
                    + ", z=" + z
                    + '}';
        }
    }
}
