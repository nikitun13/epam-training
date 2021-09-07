package by.training.thread.ex17auction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CyclicBarrier;

import static java.util.Comparator.comparingInt;

public class Auction {

    private static final Logger log
            = LogManager.getLogger(Auction.class);

    private final ArrayList<Bid> bids;
    private final CyclicBarrier barrier;

    public Auction(int bidsNumber) {
        this.bids = new ArrayList<>();
        this.barrier = new CyclicBarrier(bidsNumber, () -> {
            Bid winner = defineWinner();
            log.info("Bid #" + winner.getBidId() + ", price:" + winner.getPrice() + " win!");
        });
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public boolean add(Bid e) {
        return bids.add(e);
    }

    public Bid defineWinner() {
        return Collections.max(bids, comparingInt(Bid::getPrice));
    }
}
