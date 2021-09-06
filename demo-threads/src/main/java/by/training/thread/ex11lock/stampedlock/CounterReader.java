package by.training.thread.ex11lock.stampedlock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class CounterReader extends Thread {

    private static final Logger log
            = LogManager.getLogger(CounterReader.class);

    private final StampedLock stampedLock;
    private final CommonResource resource;

    public CounterReader(String name, StampedLock stampedLock, CommonResource resource) {
        super(name);
        this.stampedLock = stampedLock;
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                long stamp = stampedLock.tryOptimisticRead();
                int tmp = resource.counter;
                if (!stampedLock.validate(stamp)) {
                    log.info(getName() + ": protected value has been changed");
                    stamp = stampedLock.readLock();
                    log.info(getName() + ": new readLock");
                    try {
                        tmp = resource.counter;
                    } finally {
                        stampedLock.unlockRead(stamp);
                    }
                }
                log.info(getName() + ": current value:" + tmp);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            log.error(e);
            interrupt();
        }
    }
}
