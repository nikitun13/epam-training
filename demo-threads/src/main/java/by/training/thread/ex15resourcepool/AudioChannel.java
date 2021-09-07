package by.training.thread.ex15resourcepool;

import java.util.Random;

public class AudioChannel {
    private int channelId;

    public AudioChannel(int id) {
        channelId = id;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int id) {
        this.channelId = id;
    }

    public void using() {
        try {
            // использование канала
            Thread.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
