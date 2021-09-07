package by.training.thread.ex14philosopher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Philosopher extends Thread {

    private static final Logger log
            = LogManager.getLogger(Philosopher.class);

    private final Semaphore sem; // семафор. ограничивающий число философов
    // кол-во приемов пищи
    private int num = 0;
    // условный номер философа
    private final int id;

    // в качестве параметров конструктора передаем идентификатор философа и семафор
    Philosopher(Semaphore sem, int id) {
        this.sem = sem;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (num < 3) { // пока количество приемов пищи не достигнет 3
                // Запрашиваем у семафора разрешение на выполнение
                sem.acquire();
                log.info("Философ " + id + " садится за стол");

                // философ ест
                TimeUnit.MILLISECONDS.sleep(500);
                num++;

                log.info("Философ " + id + " выходит из-за стола");
                sem.release();

                // философ гуляет
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            log.error("у философа " + id + " проблемы со здоровьем", e);
        }
    }
}
