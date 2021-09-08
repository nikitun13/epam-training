package by.training.thread.ex04thread_management;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JoinThread extends Thread {

    private static final Logger log
            = LogManager.getLogger(JoinThread.class);

    public JoinThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        String nameT = getName();
        long timeout = 0;
        log.info("Start of the thread: " + nameT);
        try {
            switch (nameT) {
                case "First" -> timeout = 5_000;
                case "Second" -> timeout = 1_000;
            }
            Thread.sleep(timeout);
            log.info("finish of the thread: " + nameT);
        } catch (InterruptedException e) {
            log.error(e);
        }
    }
}
