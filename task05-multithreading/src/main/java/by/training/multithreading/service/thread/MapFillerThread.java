package by.training.multithreading.service.thread;

import by.training.multithreading.entity.Matrix;
import by.training.multithreading.util.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class MapFillerThread extends AbstractFillerThread {

    private static final Logger log
            = LogManager.getLogger(MapFillerThread.class);
    private static final int SLEEP_UP_TO;

    static {
        String property = PropertiesUtil.getProperty("thread.sleep");
        SLEEP_UP_TO = Integer.parseInt(property);
    }

    private final ConcurrentMap<Integer, Integer> map;

    public MapFillerThread(
            int value,
            Matrix matrix,
            ConcurrentMap<Integer, Integer> map) {
        super(value, matrix);
        this.map = map;
    }

    @Override
    public void run() {
        try {
            int diagonalSize = matrix.getNumberOfRows();
            int currentPosition = map.size();
            while (currentPosition < diagonalSize) {
                log.debug("trying to put {} to the position {}",
                        value, currentPosition
                );
                Integer valueAtCurrentPosition
                        = map.putIfAbsent(currentPosition, value);
                if (valueAtCurrentPosition == null) {
                    log.debug("successfully put {} to the position {}",
                            value, currentPosition
                    );
                    matrix.setElement(currentPosition, currentPosition, value);
                    log.debug("setting {} to position: {}",
                            value, currentPosition
                    );
                }
                TimeUnit.MILLISECONDS.sleep(
                        (long) (Math.random() * SLEEP_UP_TO)
                );
                currentPosition = map.size();
            }
        } catch (InterruptedException e) {
            log.error(e);
            Thread.currentThread().interrupt();
        }
    }
}
