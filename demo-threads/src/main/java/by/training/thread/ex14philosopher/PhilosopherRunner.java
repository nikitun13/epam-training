package by.training.thread.ex14philosopher;

import java.util.concurrent.Semaphore;

public class PhilosopherRunner {

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(5);
        for (int i = 1; i <= 13; i++)
            new Philosopher(sem, i).start();
    }
}
