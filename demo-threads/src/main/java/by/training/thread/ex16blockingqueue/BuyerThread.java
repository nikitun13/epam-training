package by.training.thread.ex16blockingqueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;

public class BuyerThread implements Runnable {

    private static final Logger log
            = LogManager.getLogger(BuyerThread.class);

    private final BlockingQueue<Cashbox> cashboxes;

    public BuyerThread(BlockingQueue<Cashbox> cashboxes) {
        this.cashboxes = cashboxes;
    }

    @Override
    public void run() {
        try {
            Cashbox cashbox = cashboxes.take();
            log.info(Thread.currentThread().getName() + " обслуживается в кассе " + cashbox);
            Thread.sleep(5L);
            log.info(Thread.currentThread().getName() + " освобождаю кассу " + cashbox);
            cashboxes.add(cashbox);
        } catch (InterruptedException e) {
            log.error(e);
        }
    }
}
