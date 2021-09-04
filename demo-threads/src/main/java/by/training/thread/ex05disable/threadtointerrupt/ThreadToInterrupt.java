package by.training.thread.ex05disable.threadtointerrupt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadToInterrupt extends Thread {

    private static final Logger log
            = LogManager.getLogger(ThreadToInterrupt.class);

    public ThreadToInterrupt(String name) {
        super(name);
    }

    @Override
    public void run() {
        log.info(String.format("Поток %s начал работу... \n", Thread.currentThread().getName()));
        int counter = 1;
        while (!isInterrupted()) {
            log.info("Цикл " + counter++);
        }
        log.info(String.format("Поток %s завершил работу... \n", Thread.currentThread().getName()));
    }
}
