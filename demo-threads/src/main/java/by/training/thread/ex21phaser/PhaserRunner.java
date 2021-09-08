package by.training.thread.ex21phaser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Phaser;

public class PhaserRunner {

    private static final Logger log
            = LogManager.getLogger(PhaserRunner.class);

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);

        new Thread(new PhaseThread(phaser, "PhaseThread 1")).start();
        new Thread(new PhaseThread(phaser, "PhaseThread 2")).start();

        // ждем завершения фазы 0
        int phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        log.info("Фаза " + phase + " завершена");

        // ждем завершения фазы 1
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        log.info("Фаза " + phase + " завершена");

        // ждем завершения фазы 2
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        log.info("Фаза " + phase + " завершена");

        phaser.arriveAndDeregister();
    }
}
