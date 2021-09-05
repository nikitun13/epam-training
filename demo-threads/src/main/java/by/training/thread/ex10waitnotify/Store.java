package by.training.thread.ex10waitnotify;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Store {

    private static final Logger log
            = LogManager.getLogger(Store.class);

    private int product = 0;

    public synchronized void put() {
        while (product >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
        product++;
        log.info("Производитель добавил 1 товар");
        log.info("Товаров на складе: " + product);
        notify();
    }

    public synchronized void get() {
        while (product < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
        product--;
        log.info("Покупатель купил 1 товар");
        log.info("Товаров на складе: " + product);
        notify();
    }
}
