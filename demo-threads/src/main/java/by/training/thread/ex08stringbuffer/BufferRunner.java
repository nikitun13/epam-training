package by.training.thread.ex08stringbuffer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class BufferRunner {

    private static final Logger log
            = LogManager.getLogger(BufferRunner.class);

    static int counter = 0;
    static StringBuffer s = new StringBuffer();

    public static void main(String[] args) throws InterruptedException {
        var thread = new Thread(() -> {
            synchronized (s) {
                while (counter++ < 3) {
                    s.append("A");
                    log.info("> " + counter + " " + s);
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        log.error(e);
                    }
                }
            }
        });
        thread.start();

        TimeUnit.MILLISECONDS.sleep(100);

        while (counter++ < 6) {
            log.info("< " + counter + " ");
            // в этом месте поток main будет ждать освобождения блокировки объекта s
            s.append("B");
            log.info(s);
        }
    }
}
