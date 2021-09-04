package by.training.thread.ex5disable.threadtodisable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class ThreadToDisable implements Runnable {

    private static final Logger log
            = LogManager.getLogger(ThreadToDisable.class);

    private boolean isActive;

    void disable() {
        isActive = false;
    }

    ThreadToDisable() {
        isActive = true;
    }

    public void run() {
        log.info(String.format("Поток %s начал работу... \n", Thread.currentThread().getName()));
        int counter = 1;
        while (isActive) {
            log.info("Цикл " + counter++);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                log.error("Поток прерван", e);
            }
        }
        log.info(String.format("Поток %s завершил работу... \n", Thread.currentThread().getName()));
    }
}
