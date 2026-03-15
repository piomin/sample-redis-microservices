package pl.piomin.services.trip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import pl.piomin.services.trip.model.Trip;
import tools.jackson.databind.ObjectMapper;

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
        Trip trip = mapper.readValue(message.getBody(), Trip.class);
        boolean ok = queue.offer(trip);
        LOG.info("Received body->{}, sentToQueue->{}", trip, ok);
    }
}
