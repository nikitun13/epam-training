package by.training.multithreading.service.thread;

import by.training.multithreading.entity.Matrix;
import by.training.multithreading.util.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class LockFillerThread extends AbstractFillerThread {

    private static final Logger log
            = LogManager.getLogger(LockFillerThread.class);
    private static final int SLEEP_UP_TO;

    static {
        String property = PropertiesUtil.getProperty("thread.sleep");
        SLEEP_UP_TO = Integer.parseInt(property);
    }

    private final Lock lock;

    public LockFillerThread(int value, Matrix matrix, Lock lock) {
        super(value, matrix);
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            boolean isFilled = false;
            int lastIndex = -1;
            while (!isFilled) {
                try {
                    lock.lock();
                    int indexOfEmptyDiagonalElement
                            = findIndexOfEmptyDiagonalElement(lastIndex + 1);
                    log.debug("index of empty diagonal element: {}",
                            indexOfEmptyDiagonalElement
                    );
                    if (indexOfEmptyDiagonalElement != -1) {
                        log.debug("setting {} to position: {}",
                                value, indexOfEmptyDiagonalElement
                        );
                        matrix.setElement(
                                indexOfEmptyDiagonalElement,
                                indexOfEmptyDiagonalElement,
                                value
                        );
                        lastIndex = indexOfEmptyDiagonalElement;
                    } else {
                        log.debug("matrix is filled");
                        isFilled = true;
                    }
                } finally {
                    lock.unlock();
                    TimeUnit.MILLISECONDS.sleep(
                            (long) (Math.random() * SLEEP_UP_TO)
                    );
                }
            }
        } catch (InterruptedException e) {
            log.error(e);
            Thread.currentThread().interrupt();
        }
    }
}
