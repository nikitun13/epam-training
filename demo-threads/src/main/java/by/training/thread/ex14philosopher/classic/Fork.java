package by.training.thread.ex14philosopher.classic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    private static int idGenerator = 1;

    private final int id;
    private final Lock lock = new ReentrantLock();

    public Fork() {
        id = idGenerator++;
    }

    public int getId() {
        return id;
    }

    public Lock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return "Fork{" +
                "id=" + id +
                '}';
    }
}
