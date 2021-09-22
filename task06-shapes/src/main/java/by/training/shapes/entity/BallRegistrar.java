package by.training.shapes.entity;

import by.training.shapes.service.ServiceException;
import by.training.shapes.service.ServiceFactory;
import by.training.shapes.service.VolumetricShapeCalculatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * The class {@code BallRegistrar} is an entity class
 * that keeps volume and surface area of the {@link Ball}.
 *
 * @author Nikita Romanov
 * @see Ball
 */
public class BallRegistrar implements Subscriber<Ball> {

    /**
     * Logger.
     */
    private static final Logger log = LogManager.getLogger(BallRegistrar.class);

    /**
     * Service for calculating the area and volume of the {@link Ball}.
     */
    private final VolumetricShapeCalculatorService<Ball> service
            = ServiceFactory.getInstance().getBallCalculatorService();

    /**
     * ID of the {@link Ball} for which volume and surface area will be keep.
     */
    private final int ballId;
    /**
     * Volume of the {@link Ball}.
     */
    private double volume;
    /**
     * Surface area of the {@link Ball}.
     */
    private double surfaceArea;
    /**
     * Current subscription to track {@link Ball} update.
     */
    private Subscription subscription;

    /**
     * Constructor.
     *
     * @param newBallId id of the {@link Ball} to be tracked.
     */
    public BallRegistrar(final int newBallId) {
        ballId = newBallId;
    }

    @Override
    public void onSubscribe(final Subscription newSubscription) {
        subscription = newSubscription;
        subscription.request(1);
    }

    @Override
    public void onNext(final Ball item) {
        try {
            volume = service.calculateVolume(item);
            surfaceArea = service.calculateSurfaceArea(item);
        } catch (ServiceException e) {
            log.error(e);
        }
    }

    @Override
    public void onError(final Throwable throwable) {
        log.error(throwable);
    }

    @Override
    public void onComplete() {
        // ignored
    }

    /**
     * Unsubscribes from tracking the {@link Ball}.
     */
    public void disableSubscription() {
        if (subscription != null) {
            subscription.cancel();
            subscription = null;
        }
    }

    /**
     * Getter.
     *
     * @return {@code id} of the {@code ball}.
     */
    public int getBallId() {
        return ballId;
    }

    /**
     * Getter.
     *
     * @return volume of the {@link Ball}.
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Getter.
     *
     * @return surface area of the {@link Ball}.
     */
    public double getSurfaceArea() {
        return surfaceArea;
    }

    @Override
    public String toString() {
        return "BallRegistrar{"
                + "ballId=" + ballId
                + ", volume=" + volume
                + ", surfaceArea=" + surfaceArea
                + '}';
    }
}
