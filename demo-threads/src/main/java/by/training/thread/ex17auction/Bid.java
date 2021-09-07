package by.training.thread.ex17auction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Bid extends Thread {

    private static final Logger log
            = LogManager.getLogger(Bid.class);

    private final Integer bidId;
    private int price;
    private final CyclicBarrier barrier;

    public Bid(int id, int price, CyclicBarrier barrier) {
        this.bidId = id;
        this.price = price;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            log.info("Client " + this.bidId + " specifies a price.");
            sleep(new Random().nextInt(3000)); // время на раздумье
            // определение уровня повышения цены
            int delta = new Random().nextInt(50);
            price += delta;
            log.info("Bid " + this.bidId + " : " + price);
            barrier.await(); // остановка у барьера
            log.info("Continue to work..."); // проверить кто выиграл
            // и оплатить в случае победы ставки
        } catch (BrokenBarrierException e) {
            log.error(e);
        } catch (InterruptedException e) {
            log.error(e);
        }
    }

    public Integer getBidId() {
        return bidId;
    }

    public int getPrice() {
        return price;
    }
}
