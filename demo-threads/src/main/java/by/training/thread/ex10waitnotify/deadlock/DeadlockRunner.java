package by.training.thread.ex10waitnotify.deadlock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeadlockRunner {

    private static final Logger log
            = LogManager.getLogger(DeadlockRunner.class);

    public static void main(String[] args) throws InterruptedException {
        Account account1 = new Account(20000);
        Account account2 = new Account(20000);

        AccountThread accountThread1 = new AccountThread(account1, account2);
        AccountThread accountThread2 = new AccountThread(account2, account1);

        accountThread1.start();
        accountThread2.start();

        accountThread1.join();
        accountThread2.join();

        log.info(account1);
        log.info(account2);
    }
}
