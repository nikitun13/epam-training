package by.training.thread.ex13semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

public class CountThread implements Runnable {

    private static final Logger log
            = LogManager.getLogger(CountThread.class);

    private final CommonResource res;
    private final Semaphore sem;
    private final String name;

    CountThread(CommonResource res, Semaphore sem, String name) {
        this.res = res;
        this.sem = sem;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            log.info(name + " ожидает разрешение");
            sem.acquire();
            res.x = 1;
            for (int i = 1; i < 5; i++) {
                log.info(this.name + ": " + res.x);
                res.x++;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            log.error(e);
        }
        log.info(name + " освобождает разрешение");
        sem.release();
    }
}
