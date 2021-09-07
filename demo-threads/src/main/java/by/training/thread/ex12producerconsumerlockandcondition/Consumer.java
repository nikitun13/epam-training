package by.training.thread.ex12producerconsumerlockandcondition;

public class Consumer implements Runnable {

    private final Store store;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }
}
