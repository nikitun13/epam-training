package by.training.thread.ex11lock.readwritelock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

public class WriteEven extends Thread {

    private static final Logger log
            = LogManager.getLogger(WriteEven.class);

    private final ReadWriteLock readWriteLock;
    private final List<Integer> integers;

    public WriteEven(ReadWriteLock readWriteLock, List<Integer> integers) {
        this.readWriteLock = readWriteLock;
        this.integers = integers;
    }

    @Override
    public void run() {
        for (int i = 2; i <= 20; i += 2) {
            try {
                readWriteLock.writeLock().lock();
                log.info("Writing even number");
                integers.add(i);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
    }
}
