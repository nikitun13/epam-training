package by.training.thread.ex17auction;

import java.util.Random;

public class AuctionRunner {

    public static void main(String[] args) {
        final int bidsNumber = 5;
        Auction auction = new Auction(bidsNumber);
        int startPrice = new Random().nextInt(100);
        for (int i = 0; i < bidsNumber; i++) {
            Bid thread = new Bid(i, startPrice, auction.getBarrier());
            auction.add(thread);
            thread.start();
        }
    }
}
