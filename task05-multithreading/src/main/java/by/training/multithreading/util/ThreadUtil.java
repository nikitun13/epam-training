package by.training.multithreading.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public final class ThreadUtil {

    private static final Logger log
            = LogManager.getLogger(ThreadUtil.class);

    private ThreadUtil() {
    }

    public static void joinThreads(List<? extends Thread> threads) {
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            log.error(e);
            Thread.currentThread().interrupt();
        }
    }
}
