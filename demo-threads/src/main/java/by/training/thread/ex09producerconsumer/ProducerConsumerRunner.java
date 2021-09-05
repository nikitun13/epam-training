package by.training.thread.ex09producerconsumer;

public class ProducerConsumerRunner {

    public static void main(String[] args) {
        Store store = new Store();
        new Producer(store, "Producer").start();
        new Consumer(store, "Consumer").start();
    }
}
