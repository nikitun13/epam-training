package by.training.thread.ex09producerconsumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Producer extends Thread {

    private static final Logger log
            = LogManager.getLogger(Producer.class);

    private final Store store; // объект склада, куда кладем товар
    private int product = 5; // количество товаров, которые надо добавить

    public Producer(Store store, String name) {
        super(name);
        this.store = store;
    }

    public void run() {
        try {
            while (product > 0) { // пока у производителя имеются товары
                product = product - store.put(); // кладем один товар на склад
                log.info("производителю осталось произвести " + product + " товар(ов)");
                sleep(100); // время простоя
            }
        } catch (InterruptedException e) {
            log.error("поток производителя прерван", e);
        }
    }
}
