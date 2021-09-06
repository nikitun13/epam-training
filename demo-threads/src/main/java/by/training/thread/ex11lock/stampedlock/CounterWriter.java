package by.training.thread.ex11lock.stampedlock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class CounterWriter extends Thread {

    private static final Logger log
            = LogManager.getLogger(CounterWriter.class);

    private final StampedLock stampedLock;
    private final CommonResource resource;

    public CounterWriter(String name, StampedLock stampedLock, CommonResource resource) {
        super(name);
        this.stampedLock = stampedLock;
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                long stamp = stampedLock.writeLock();
                try {
                    int tmp = resource.counter;
                    log.info("start counter modification:" + tmp);
                    TimeUnit.SECONDS.sleep(5);
                    tmp++;
                    resource.counter = tmp;
                    log.info("end counter modification:" + tmp);

                } finally {
                    stampedLock.unlockWrite(stamp);
                }
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (InterruptedException e) {
            log.error(e);
            interrupt();
        }
    }
}
