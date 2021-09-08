package by.training.thread.ex02runnable_person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunnablePersonRunner {

    private static final Logger log
            = LogManager.getLogger(RunnablePersonRunner.class);

    public static void main(String[] args) {
        log.info("======== " + Thread.currentThread().getName() + " started ========");

        RunnablePerson alice = new RunnablePerson("Alice");
        RunnablePerson bob = new RunnablePerson("Bob");

        Thread aliceThread = new Thread(alice, "Alice Thread");
        Thread bobThread = new Thread(bob, "Bob Thread");

        aliceThread.start();
        bobThread.start();

        log.info("======== " + Thread.currentThread().getName() + " finished ========");
    }
}
