package by.training.shapes.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * The class {@code Ball} is
 * an entity class that represents geometric
 * shape of ball in three dimensions.
 *
 * @author Nikita Romanov
 */
public class Ball implements VolumetricShape, Publisher<Ball> {

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
     * List of Subscribers who listen for changes in radius.
     */
    private final List<Subscriber<? super Ball>> subscribers;

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
        this(newRadius, newCenterPoint);
        id = newId;
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
        subscribers = new ArrayList<>();
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
        notifyAllSubscribers();
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

    @Override
    public void subscribe(final Subscriber<? super Ball> subscriber) {
        if (subscribers.contains(subscriber)) {
            subscriber.onError(new IllegalStateException("already subscribed"));
        } else {
            subscribers.add(subscriber);
            subscriber.onSubscribe(new BallSubscription(subscriber));
        }
    }

    private void notifyAllSubscribers() {
        subscribers.forEach(subscriber -> subscriber.onNext(this));
    }

    private final class BallSubscription implements Subscription {

        /**
         * {@link Subscriber} of this {@link Subscription}.
         */
        private final Subscriber<? super Ball> subscriber;
        /**
         * Subscription is completed or not.
         */
        private boolean completed;

        private BallSubscription(final Subscriber<? super Ball> newSubscriber) {
            subscriber = newSubscriber;
        }

        @Override
        public void request(final long n) {
            if (n != 0 && !completed) {
                if (n < 0) {
                    IllegalArgumentException ex = new IllegalArgumentException(
                            "n can't be less than or equal to zero"
                    );
                    subscriber.onError(ex);
                }
                subscriber.onNext(Ball.this);
            }
        }

        @Override
        public void cancel() {
            completed = true;
            subscribers.remove(subscriber);
        }
    }
}
