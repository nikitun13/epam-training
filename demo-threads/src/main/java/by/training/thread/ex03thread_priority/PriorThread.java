package by.training.thread.ex03thread_priority;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PriorThread extends Thread {

    private static final Logger log
            = LogManager.getLogger(PriorThread.class);

    public PriorThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            log.info(getName() + " " + i);
            try {
                Thread.sleep(0); // try sleep(1),sleep(0),sleep(10)
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
    }
}
