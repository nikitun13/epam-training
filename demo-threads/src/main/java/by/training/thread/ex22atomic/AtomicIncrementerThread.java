package by.training.thread.ex22atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIncrementerThread extends Thread {

    private final AtomicInteger resource;

    public AtomicIncrementerThread(String name, AtomicInteger resource) {
        super(name);
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            resource.incrementAndGet();
        }
    }
}
