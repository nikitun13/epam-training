package by.training.thread.ex5disable.threadtodisable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadToDisableRunner {

    private static final Logger log
            = LogManager.getLogger(ThreadToDisableRunner.class);

    public static void main(String[] args) {
        log.info("Главный поток начал работу...");
        ThreadToDisable myThread = new ThreadToDisable();
        Thread myT = new Thread(myThread, "ThreadToDisable");
        myT.start();
        try {
            Thread.sleep(1100);

            myThread.disable();

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("Поток прерван", e);
        }
        log.info("Главный поток завершил работу...");
    }
}
