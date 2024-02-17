package pl.piomin.services.passenger.subscribe;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piomin.services.passenger.model.Passenger;
import pl.piomin.services.passenger.model.Trip;
import pl.piomin.services.passenger.repository.PassengerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class PassengerSubscriber implements MessageListener {

    private final Logger LOGGER = LoggerFactory.getLogger(PassengerSubscriber.class);

    @Autowired
    PassengerRepository repository;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] bytes) {
        try {
            Trip trip = mapper.readValue(message.getBody(), Trip.class);
            LOGGER.info("Message received: {}", trip.toString());
            Optional<Passenger> optPassenger = repository.findById(trip.getDriverId());
            if (optPassenger.isPresent()) {
                repository.save(optPassenger.get());
            }
        } catch (IOException e) {
            LOGGER.error("Error reading message", e);
        }
    }

}
