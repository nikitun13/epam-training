package by.training.thread.ex13semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreRunner {

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        CommonResource res = new CommonResource();
        new Thread(new CountThread(res, sem, "CountThread 1")).start();
        new Thread(new CountThread(res, sem, "CountThread 2")).start();
        new Thread(new CountThread(res, sem, "CountThread 3")).start();
    }
}
