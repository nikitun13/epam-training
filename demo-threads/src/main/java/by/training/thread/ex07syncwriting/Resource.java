package by.training.thread.ex07syncwriting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class Resource {

    private static final Logger log
            = LogManager.getLogger(Resource.class);

    private FileWriter fileWriter;

    public Resource(String file) throws IOException {
        fileWriter = new FileWriter(file, true);
        fileWriter.append('\n');
    }

    public synchronized void writing(String str, int i) {
        try {
            fileWriter.append(str + i);
            log.info(str + i);
            Thread.sleep((long) (Math.random() * 50));
            fileWriter.append("->" + i + " ");
            log.info("->" + i + " ");
        } catch (IOException e) {
            log.error("ошибка файла: ", e);
        } catch (InterruptedException e) {
            log.error("ошибка потока: ", e);
        }
    }

    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            log.error("ошибка закрытия файла: ", e);
        }
    }
}
