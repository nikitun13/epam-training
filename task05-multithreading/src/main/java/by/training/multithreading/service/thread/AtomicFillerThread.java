package by.training.multithreading.service.thread;

import by.training.multithreading.entity.Matrix;
import by.training.multithreading.util.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicFillerThread extends AbstractFillerThread {

    private static final Logger log
            = LogManager.getLogger(AtomicFillerThread.class);
    private static final int SLEEP_UP_TO;

    static {
        String property = PropertiesUtil.getProperty("thread.sleep");
        SLEEP_UP_TO = Integer.parseInt(property);
    }

    private final AtomicInteger atomicInteger;

    public AtomicFillerThread(
            int value,
            Matrix matrix,
            AtomicInteger atomicInteger) {
        super(value, matrix);
        this.atomicInteger = atomicInteger;
    }

    @Override
    public void run() {
        try {
            boolean isFilled = false;
            int diagonalSize = matrix.getNumberOfRows();
            while (!isFilled) {
                int position = atomicInteger.incrementAndGet();
                if (position < diagonalSize) {
                    log.debug("setting {} to position: {}",
                            value, position
                    );
                    matrix.setElement(
                            position,
                            position,
                            value
                    );
                    TimeUnit.MILLISECONDS.sleep(
                            (long) (Math.random() * SLEEP_UP_TO)
                    );
                } else {
                    log.debug("matrix is filled");
                    isFilled = true;
                }
            }
        } catch (InterruptedException e) {
            log.error(e);
            Thread.currentThread().interrupt();
        }
    }
}
