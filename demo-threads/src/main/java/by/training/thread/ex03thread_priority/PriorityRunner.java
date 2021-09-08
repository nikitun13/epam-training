package by.training.thread.ex03thread_priority;

public class PriorityRunner {

    public static void main(String[] args) {
        PriorThread min = new PriorThread("Min");
        PriorThread max = new PriorThread("Max");
        PriorThread norm = new PriorThread("Norm");
        PriorThread betweenNormAndMax = new PriorThread("upper norm");

        min.setPriority(Thread.MIN_PRIORITY); // 1
        max.setPriority(Thread.MAX_PRIORITY); // 10
        norm.setPriority(Thread.NORM_PRIORITY); // 5
        betweenNormAndMax.setPriority(7);

        min.start();
        norm.start();
        max.start();
        betweenNormAndMax.start();
    }
}
