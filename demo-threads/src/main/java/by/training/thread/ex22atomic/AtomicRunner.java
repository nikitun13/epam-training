package by.training.thread.ex22atomic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class AtomicRunner {

    private static final Logger log
            = LogManager.getLogger(AtomicRunner.class);

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomic = new AtomicInteger();
        Stream.iterate(1, integer -> integer++)
                .map(integer -> new AtomicIncrementerThread(integer + " atomic thread", atomic))
                .limit(5)
                .forEach(AtomicIncrementerThread::start);
        TimeUnit.SECONDS.sleep(3L);
        int result = atomic.get();
        log.info("result: {}", result);
    }
}
