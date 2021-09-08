package by.training.thread.ex19executor_service.second_example;

import java.util.ArrayDeque;
import java.util.List;

public class ProductList {

    private static final ArrayDeque<String> arr = new ArrayDeque<>(List.of(
            "Product 1",
            "Product 2",
            "Product 3",
            "Product 4",
            "Product 5"
    ));

    public static String getProduct() {
        return arr.poll();
    }
}