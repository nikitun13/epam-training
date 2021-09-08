package by.training.thread.ex19executor_service.second_example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolRunner {

    private static final Logger log
            = LogManager.getLogger(ThreadPoolRunner.class);

    public static void main(String[] args) throws Exception {
        ArrayList<Future<String>> list = new ArrayList<>();
        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 7; i++) {
            list.add(es.submit(new BaseCallable()));
        }
        es.shutdown();
        for (Future<String> future : list) {
            log.info(future.get() + " result fixed");
        }
    }
}
