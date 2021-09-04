package by.training.thread.ex07syncwriting;

import java.util.concurrent.TimeUnit;

public class WriterThread extends Thread {

    private Resource rs;

    public WriterThread(String name, Resource rs) {
        super(name);
        this.rs = rs;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            rs.writing(getName(), i);
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
