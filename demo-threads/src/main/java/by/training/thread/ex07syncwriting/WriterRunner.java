package by.training.thread.ex07syncwriting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class WriterRunner {

    private static final Logger log
            = LogManager.getLogger(WriterRunner.class);

    public static void main(String[] args) {
        Resource s = null;
        try {
            s = new Resource("src/main/resources/data/writing-test.txt");
            WriterThread t1 = new WriterThread("First", s);
            WriterThread t2 = new WriterThread("Second", s);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (IOException e) {
            log.error("ошибка файла: ", e);
        } catch (InterruptedException e) {
            log.error("ошибка потока: ", e);
        } finally {
            assert s != null;
            s.close();
        }
    }
}
