package by.training.thread.ex01hello_world;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloWorldThread extends Thread {

    private static final Logger log
            = LogManager.getLogger(HelloWorldThread.class);

    public HelloWorldThread() {
    }

    public HelloWorldThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Hello world");
        log.info("custom thread");
    }
}
