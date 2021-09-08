package by.training.thread.ex20exchanger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Exchanger;

public class ThreadForExchange extends Thread {

    private static final Logger log
            = LogManager.getLogger(ThreadForExchange.class);

    private final Exchanger<String> exchanger;

    public ThreadForExchange(String name, Exchanger<String> ex) {
        super(name);
        this.exchanger = ex;
    }

    @Override
    public void run() {
        try {
            String message = " - It is the message from: " + Thread.currentThread().getName();
            message = exchanger.exchange(message);
            log.info(Thread.currentThread().getName() + " get message: " + message);
        } catch (InterruptedException e) {
            log.error(e);
        }
    }
}
