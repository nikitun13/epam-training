package by.training.thread.ex11lock.readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockRunner {

    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
        List<Integer> integers = new ArrayList<>();

        Thread read1 = new Read(readWriteLock, integers);
        read1.setName("read1");
        Thread read2 = new Read(readWriteLock, integers);
        read2.setName("read2");
        Thread writeOdd = new WriteOdd(readWriteLock, integers);
        writeOdd.setName("Write odd");
        Thread writeEven = new WriteEven(readWriteLock, integers);
        writeEven.setName("Write even");

        writeOdd.start();
        read1.start();
        writeEven.start();
        read2.start();
    }
}
