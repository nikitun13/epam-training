package by.training.thread.ex19executor_service.first_example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableRunner {

    private static final Logger log
            = LogManager.getLogger(CallableRunner.class);

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Number> future = threadPool.submit(new CalcCallable());
        threadPool.shutdown();
        try {
            log.info(future.get());
        } catch (InterruptedException | ExecutionException e) {
            log.error(e);
        }
    }
}
