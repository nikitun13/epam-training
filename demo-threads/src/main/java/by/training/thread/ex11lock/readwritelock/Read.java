package by.training.thread.ex11lock.readwritelock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

public class Read extends Thread {

    private static final Logger log
            = LogManager.getLogger(Read.class);

    private final ReadWriteLock readWriteLock;
    private final List<Integer> integers;

    public Read(ReadWriteLock readWriteLock, List<Integer> integers) {
        this.readWriteLock = readWriteLock;
        this.integers = integers;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 15; i++) {
            readWriteLock.readLock().lock();
            log.info(Thread.currentThread().getName() + " --- Value: " + integers);
            readWriteLock.readLock().unlock();
        }
    }
}
