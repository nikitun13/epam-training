package by.training.thread.ex20exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerRunner {

    public static void main(String[] args) {
        Exchanger<String> ex = new Exchanger<>();
        Thread t1 = new ThreadForExchange("First thread for exchange", ex);
        Thread t2 = new ThreadForExchange("Second thread for exchange", ex);
        t1.start();
        t2.start();
    }
}
