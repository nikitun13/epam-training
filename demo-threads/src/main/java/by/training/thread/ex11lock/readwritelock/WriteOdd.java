package by.training.thread.ex11lock.readwritelock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

public class WriteOdd extends Thread {

    private static final Logger log
            = LogManager.getLogger(WriteOdd.class);

    private final ReadWriteLock readWriteLock;
    private final List<Integer> integers;

    public WriteOdd(ReadWriteLock readWriteLock, List<Integer> integers) {
        this.readWriteLock = readWriteLock;
        this.integers = integers;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 15; i += 2) {
            try {
                readWriteLock.writeLock().lock();
                log.info("Writing Odd number");
                integers.add(i);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
    }
}
