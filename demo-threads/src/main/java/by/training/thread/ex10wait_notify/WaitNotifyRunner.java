package by.training.thread.ex10wait_notify;

public class WaitNotifyRunner {

    public static void main(String[] args) {
        Store store = new Store();
        new Producer(store, "Producer").start();
        new Consumer(store, "Consumer").start();
    }
}
