package by.training.shapes.entity;

/**
 * The class {@code Plane} is an enumeration
 * of coordinate planes.
 *
 * @author Nikita Romanov
 */
public enum Plane {

    /**
     * Represent XY-plane.
     */
    XY_PLANE(Axis.X_AXIS, Axis.Y_AXIS),
    /**
     * Represent XZ-plane.
     */
    XZ_PLANE(Axis.X_AXIS, Axis.Y_AXIS),
    /**
     * Represent YZ-plane.
     */
    YZ_PLANE(Axis.Y_AXIS, Axis.Z_AXIS);

    /**
     * First {@link Axis} of the plane.
     */
    private final Axis firstAxis;
    /**
     * Second {@link Axis} of the plane.
     */
    private final Axis secondAxis;

    Plane(final Axis firstAxis, final Axis secondAxis) {
        this.firstAxis = firstAxis;
        this.secondAxis = secondAxis;
    }

    public Axis getFirstAxis() {
        return firstAxis;
    }

    public Axis getSecondAxis() {
        return secondAxis;
    }
}
