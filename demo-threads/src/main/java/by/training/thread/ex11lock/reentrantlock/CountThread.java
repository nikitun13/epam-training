package by.training.thread.ex11lock.reentrantlock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class CountThread implements Runnable {

    private static final Logger log
            = LogManager.getLogger(CountThread.class);

    private final CommonResource res;
    private final ReentrantLock locker;

    CountThread(CommonResource res, ReentrantLock lock) {
        this.res = res;
        locker = lock;
    }

    public void run() {
        locker.lock(); // устанавливаем блокировку
        try {
            res.x = 1;
            for (int i = 1; i < 5; i++) {
                log.info(String.format("%s %d", Thread.currentThread().getName(), res.x));
                res.x++;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            log.error(e);
        } finally {
            locker.unlock(); // снимаем блокировку
        }
    }
}
