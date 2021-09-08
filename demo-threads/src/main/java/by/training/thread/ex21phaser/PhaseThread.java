package by.training.thread.ex21phaser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Phaser;

public class PhaseThread implements Runnable {

    private static final Logger log
            = LogManager.getLogger(PhaseThread.class);

    private final Phaser phaser;
    private final String name;

    PhaseThread(Phaser p, String n) {

        this.phaser = p;
        this.name = n;
        phaser.register();
    }

    @Override
    public void run() {
        log.info(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // сообщаем, что первая фаза достигнута
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            log.error(e);
        }

        log.info(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // сообщаем, что вторая фаза достигнута
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            log.error(e);
        }
        log.info(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndDeregister(); // сообщаем о завершении фаз и удаляем с регистрации объекты
    }
}
