package by.training.thread.ex15resourcepool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ChannelPool<T> {

    private final Semaphore semaphore;
    private final Queue<T> resources = new LinkedList<>();

    public ChannelPool(Queue<T> source, int poolSize) {
        semaphore = new Semaphore(poolSize, true);
        resources.addAll(source);
    }

    public T getResource(long maxWaitMillis) throws ResourceException {
        try {
            if (semaphore.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS)) {
                return resources.poll();
            }
        } catch (InterruptedException e) {
            throw new ResourceException(e);
        }
        throw new ResourceException(":превышено время ожидания");
    }

    public void returnResource(T res) {
        resources.add(res); // возвращение экземпляра в пул
        semaphore.release();
    }
}
