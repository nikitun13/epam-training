package by.training.thread.ex06commonresource;

import by.training.thread.ex06commonresource.resource.CommonResource;
import by.training.thread.ex06commonresource.resource.SyncCommonResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class CommonResourceRunner {

    private static final Logger log
            = LogManager.getLogger(CommonResourceRunner.class);

    public static void main(String[] args) throws InterruptedException {
        log.info("main started");
        countThreadRunner();
        TimeUnit.SECONDS.sleep(2);
        log.info("test count thread (without sync) finished\n");
        syncCountThreadRunner();
        TimeUnit.SECONDS.sleep(3);
        log.info("test sync count thread finished\n");
        syncCommonResourceRunner();
        TimeUnit.SECONDS.sleep(3);
        log.info("test sync resource finished\n");
        log.info("main finished");
    }

    private static void countThreadRunner() {
        log.info("test count thread (without sync)");
        CommonResource commonResource = new CommonResource();
        for (int i = 1; i < 6; i++) {
            Thread t = new Thread(new CountThread(commonResource));
            t.setName("Поток " + i);
            t.start();
        }
    }

    private static void syncCountThreadRunner() {
        log.info("test sync count thread");
        CommonResource commonResource = new CommonResource();
        for (int i = 1; i < 6; i++) {
            Thread t = new Thread(new SyncCountThread(commonResource));
            t.setName("Поток " + i);
            t.start();
        }
    }

    private static void syncCommonResourceRunner() {
        log.info("test sync resource");
        SyncCommonResource commonResource = new SyncCommonResource();
        for (int i = 1; i < 6; i++) {
            Thread t = new Thread(commonResource::increment);
            t.setName("Поток " + i);
            t.start();
        }
    }
}
