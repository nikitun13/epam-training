package by.training.thread.ex19executor_service.second_example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class BaseCallable implements Callable<String> {

    private static final Logger log
            = LogManager.getLogger(BaseCallable.class);

    @Override
    public String call() throws Exception {
        String product = ProductList.getProduct();
        String result;
        if (product != null) {
            result = product + " done";
        } else {
            result = "productList is empty";
        }
        TimeUnit.MILLISECONDS.sleep(100);
        log.info(result);
        return result;
    }
}
