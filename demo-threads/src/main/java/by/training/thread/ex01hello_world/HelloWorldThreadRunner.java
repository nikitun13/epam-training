package by.training.thread.ex01hello_world;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloWorldThreadRunner {

    private static final Logger log
            = LogManager.getLogger(HelloWorldThreadRunner.class);

    public static void main(String[] args) throws InterruptedException {
        log.info("main thread");
        HelloWorldThread t = new HelloWorldThread();
        Thread.State state = t.getState();
        System.out.println(state);
        t.start();
        state = t.getState();
        System.out.println(state);
        Thread.sleep(1);
        state = t.getState();
        System.out.println(state);
    }
}
