package by.training.shapes.entity;

import java.util.Objects;

/**
 * Mark classes as classes that represent
 * volumetric shape entities.
 *
 * @author Nikita Romanov
 */
public interface VolumetricShape {

    /**
     * The class {@code Point} is a class
     * that represents point in three-dimensional space.
     *
     * @author Nikita Romanov
     */
    class Point {

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
