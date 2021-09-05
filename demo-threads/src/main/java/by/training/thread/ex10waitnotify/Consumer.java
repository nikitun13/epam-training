package by.training.thread.ex10waitnotify;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Consumer extends Thread {

    private static final Logger log
            = LogManager.getLogger(Consumer.class);

    private final Store store;

    public Consumer(Store store, String name) {
        super(name);
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }
}
