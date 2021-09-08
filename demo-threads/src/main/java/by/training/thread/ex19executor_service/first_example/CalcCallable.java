package by.training.thread.ex19executor_service.first_example;

import java.util.Random;
import java.util.concurrent.Callable;

public class CalcCallable implements Callable<Number> {

    @Override
    public Number call() {
        return new Random().nextGaussian();
    }
}
