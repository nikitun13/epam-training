package by.training.thread.ex14philosopher.classic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Philosopher extends Thread {

    private static int idGenerator = 1;
    private static final Logger log
            = LogManager.getLogger(Philosopher.class);

    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(String name, Fork leftFork, Fork rightFork) {
        super(name);
        id = idGenerator++;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                log.info(String.format("(%d) %s want to eat", id, getName()));
                lockForks();
                log.info(String.format("(%d) %s start eating...", id, getName()));
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
                log.info(String.format("(%d) %s stopped eating and stated philosophizing", id, getName()));
            } catch (InterruptedException e) {
                log.error(e);
            } finally {
                unlockForks();
            }
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 2000));
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
    }

    private void lockForks() {
        Lock leftForkLock = leftFork.getLock();
        Lock rightForkLock = rightFork.getLock();
        while (true) {
            boolean leftForkLockResult = leftForkLock.tryLock();
            boolean rightForkLockResult = rightForkLock.tryLock();
            if (leftForkLockResult && rightForkLockResult) {
                break;
            }
            if (leftForkLockResult) {
                leftForkLock.unlock();
            }
            if (rightForkLockResult) {
                rightForkLock.unlock();
            }
        }
    }

    private void unlockForks() {
        leftFork.getLock().unlock();
        rightFork.getLock().unlock();
    }
}
