package by.training.thread.ex04threadmanagement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunnerJoin {

    private static final Logger log
            = LogManager.getLogger(RunnerJoin.class);

    static {
        log.info("Start of the thread: main");
    }

    public static void main(String[] args) {
        JoinThread first = new JoinThread("First");
        JoinThread second = new JoinThread("Second");
        first.start();
        second.start();
        try {
            second.join(500);
        } catch (InterruptedException e) {
            log.error(e);
        }
        log.info(Thread.currentThread().getName()); // name of current thread
    }
}
