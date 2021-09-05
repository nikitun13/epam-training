package by.training.thread.ex10waitnotify;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Producer extends Thread {

    private static final Logger log
            = LogManager.getLogger(Producer.class);

    private final Store store;

    public Producer(Store store, String name) {
        super(name);
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.put();
        }
    }
}
