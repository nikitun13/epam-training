package by.training.thread.ex15resource_pool;

import java.util.LinkedList;
import java.util.List;

public class SourcePoolRunner {

    public static void main(String[] args) {
        LinkedList<AudioChannel> list = new LinkedList<>(List.of(
                new AudioChannel(771),
                new AudioChannel(883),
                new AudioChannel(550),
                new AudioChannel(337),
                new AudioChannel(442)
        ));
        ChannelPool<AudioChannel> pool = new ChannelPool<>(list, 5);
        for (int i = 0; i < 20; i++) {
            new Client(pool).start();
        }
    }
}
