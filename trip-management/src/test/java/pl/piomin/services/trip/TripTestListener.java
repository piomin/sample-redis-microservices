package pl.piomin.services.trip;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import pl.piomin.services.trip.model.Trip;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class TripTestListener implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(TripTestListener.class);
    private BlockingQueue<Trip> queue;

    public TripTestListener(BlockingQueue<Trip> queue) {
        this.queue = queue;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Trip trip = mapper.readValue(message.getBody(), Trip.class);
            boolean ok = queue.offer(trip);
            LOG.info("Received body->{}, sentToQueue->{}", trip, ok);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
