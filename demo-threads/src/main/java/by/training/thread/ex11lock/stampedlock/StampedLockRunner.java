package by.training.thread.ex11lock.stampedlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class StampedLockRunner {

    public static void main(String[] args) throws InterruptedException {
        CommonResource resource = new CommonResource();
        StampedLock lock = new StampedLock();
        Thread reader1 = new CounterReader("Reader 1", lock, resource);
        Thread reader2 = new CounterReader("Reader 2", lock, resource);
        Thread writer = new CounterWriter("writer", lock, resource);

        reader1.start();
        reader2.start();
        writer.start();

        TimeUnit.SECONDS.sleep(20);
        reader1.interrupt();
        reader2.interrupt();
        writer.interrupt();
    }
}
