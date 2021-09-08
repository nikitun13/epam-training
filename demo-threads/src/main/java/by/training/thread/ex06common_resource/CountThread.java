package by.training.thread.ex06common_resource;

import by.training.thread.ex06common_resource.resource.CommonResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CountThread implements Runnable {

    private static final Logger log
            = LogManager.getLogger(CountThread.class);

    private CommonResource res;

    CountThread(CommonResource res) {
        this.res = res;
    }

    @Override
    public void run() {
        res.x = 1;
        for (int i = 1; i < 5; i++) {
            log.info(String.format("%s %d", Thread.currentThread().getName(), res.x));
            res.x++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
    }
}

