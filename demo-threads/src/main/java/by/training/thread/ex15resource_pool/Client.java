package by.training.thread.ex15resource_pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client extends Thread {

    private static final Logger log
            = LogManager.getLogger(Client.class);

    private boolean reading = false;
    private final ChannelPool<AudioChannel> pool;

    public Client(ChannelPool<AudioChannel> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        AudioChannel channel = null;
        try {
            channel = pool.getResource(500);
            reading = true;
            log.info("Channel Client #" + this.getId()
                    + " took channel #" + channel.getChannelId());
            channel.using();
        } catch (ResourceException e) {
            log.info("Client #" + this.getId() + " lost ->"
                    + e.getMessage());
        } finally {
            if (channel != null) {
                reading = false;
                log.info("Channel Client #" + this.getId() + " : "
                        + channel.getChannelId() + " channel released");
                pool.returnResource(channel);
            }
        }
    }

    public boolean isReading() {
        return reading;
    }
}
