package by.training.thread.ex11lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockRunner {
    public static void main(String[] args) {
        CommonResource commonResource = new CommonResource();
        ReentrantLock locker = new ReentrantLock();
        for (int i = 1; i < 6; i++) {
            Thread t = new Thread(new CountThread(commonResource, locker));
            t.setName("Поток " + i);
            t.start();
        }
    }
}
