package by.training.thread.ex12producer_consumer_lock_and_condition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Store {

    private static final Logger log
            = LogManager.getLogger(Store.class);

    private int product = 0;
    private final ReentrantLock lock;
    private final Condition notFullCondition;
    private final Condition notEmptyCondition;

    public Store() {
        lock = new ReentrantLock();
        notFullCondition = lock.newCondition();
        notEmptyCondition = lock.newCondition();
    }

    public void get() {

        lock.lock();
        try {
            // пока нет доступных товаров на складе, ожидаем
            while (product < 1) {
                log.info(Thread.currentThread().getName() + " ожидает");
                notEmptyCondition.await();
            }
            product--;
            log.info("Покупатель купил 1 товар");
            log.info("Товаров на складе: " + product);
            notFullCondition.signalAll();
        } catch (InterruptedException e) {
            log.error(e);
        } finally {
            lock.unlock();
        }
    }

    public void put() {

        lock.lock();
        try {
            // пока на складе 3 товара, ждем освобождения места
            while (product >= 3) {
                log.info(Thread.currentThread().getName() + " ожидает");
                notFullCondition.await();
            }
            product++;
            log.info("Производитель добавил 1 товар");
            log.info("Товаров на складе: " + product);
            notEmptyCondition.signalAll();
        } catch (InterruptedException e) {
            log.error(e);
        } finally {
            lock.unlock();
        }
    }
}
