package by.training.thread.ex5disable.daemonthread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class DaemonThread extends Thread {

    private static final Logger log
            = LogManager.getLogger(DaemonThread.class);

    public DaemonThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        log.info(currentThread().getName() + " started");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error(e);
        } finally {
            log.info(currentThread().getName() + " finished");
        }
    }
}
