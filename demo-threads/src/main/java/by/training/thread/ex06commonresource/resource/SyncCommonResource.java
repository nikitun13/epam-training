package by.training.thread.ex06commonresource.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SyncCommonResource {

    private static final Logger log
            = LogManager.getLogger(SyncCommonResource.class);

    public int x;

    public synchronized void increment() {
        x = 1;
        for (int i = 1; i < 5; i++) {
            log.info(String.format("%s %d", Thread.currentThread().getName(), x));
            x++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
    }
}
