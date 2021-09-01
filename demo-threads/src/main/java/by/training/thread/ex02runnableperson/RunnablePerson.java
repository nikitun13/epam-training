package by.training.thread.ex02runnableperson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class RunnablePerson extends Person implements Runnable {

    private static final Logger log
            = LogManager.getLogger(RunnablePerson.class);

    public RunnablePerson(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; ++i) {
            log.info(i + ") Hello: " + getName());
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
    }
}
