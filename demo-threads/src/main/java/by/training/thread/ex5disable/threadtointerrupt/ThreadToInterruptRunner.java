package by.training.thread.ex5disable.threadtointerrupt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadToInterruptRunner {

    private static final Logger log
            = LogManager.getLogger(ThreadToInterruptRunner.class);

    public static void main(String[] args) {
        log.info("Главный поток начал работу...");
        Thread thread = new ThreadToInterrupt("ThreadToInterrupt");
        thread.start();
        try {
            Thread.sleep(5);

            thread.interrupt();

            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("Поток прерван", e);
        }
        log.info("Главный поток завершил работу...");
    }
}
