package by.training.thread.ex12producerconsumerlockandcondition;

public class ProducerConsumerRunner {

    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer, "Producer").start();
        new Thread(consumer, "Consumer").start();
    }
}
