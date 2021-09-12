package by.training.multithreading.service.thread;

import by.training.multithreading.entity.Matrix;
import by.training.multithreading.util.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

public class SetFillerThread extends AbstractFillerThread {

    private static final Logger log
            = LogManager.getLogger(SetFillerThread.class);
    private static final int SLEEP_UP_TO;

    static {
        String property = PropertiesUtil.getProperty("thread.sleep");
        SLEEP_UP_TO = Integer.parseInt(property);
    }

    private final ConcurrentSkipListSet<Integer> set;

    public SetFillerThread(
            int value,
            Matrix matrix,
            ConcurrentSkipListSet<Integer> set) {
        super(value, matrix);
        this.set = set;
    }

    @Override
    public void run() {
        try {
            int diagonalSize = matrix.getNumberOfRows();
            int currentPosition = set.size();
            while (currentPosition < diagonalSize) {
                log.debug("trying to add {} to the position {}",
                        value, currentPosition
                );
                if (set.add(currentPosition)) {
                    log.debug("successfully added {} to the position {}",
                            value, currentPosition
                    );
                    matrix.setElement(currentPosition, currentPosition, value);
                    log.debug("setting {} to position: {}",
                            value, currentPosition
                    );
                    TimeUnit.MILLISECONDS.sleep(
                            (long) (Math.random() * SLEEP_UP_TO)
                    );
                }
                currentPosition = set.size();
            }
        } catch (InterruptedException e) {
            log.error(e);
            Thread.currentThread().interrupt();
        }
    }
}
