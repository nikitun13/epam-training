package by.training.thread.ex05disable.daemonthread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class DaemonThreadRunner {

    private static final Logger log
            = LogManager.getLogger(DaemonThreadRunner.class);

    public static void main(String[] args) {
        log.info(Thread.currentThread().getName() + " started");

        Thread daemonThread = new DaemonThread("DaemonThread");
        daemonThread.setDaemon(true);
        daemonThread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error(e);
        }
        log.info(Thread.currentThread().getName() + " finished");
    }
}
