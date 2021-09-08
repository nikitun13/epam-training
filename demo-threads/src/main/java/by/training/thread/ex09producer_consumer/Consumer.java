package by.training.thread.ex09producer_consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Consumer extends Thread {

    private static final Logger log
            = LogManager.getLogger(Consumer.class);

    private final Store store; // объект склада, с которого покупатель будет брать товар
    private int product = 0; // текущее количество товаров со склада
    private final int maxNumber = 5; // максимально допустимое число

    public Consumer(Store store, String name) {
        super(name);
        this.store = store;
    }

    public void run() {
        try {
            while (product < maxNumber) { // пока количество товаров не будет равно 5
                product = product + store.get(); // берем по одному товару со склада
                log.info("Потребитель купил " + product + " товар(ов)");
                sleep(100);
            }
        } catch (InterruptedException e) {
            log.error("поток потребителя прерван", e);
        }
    }
}