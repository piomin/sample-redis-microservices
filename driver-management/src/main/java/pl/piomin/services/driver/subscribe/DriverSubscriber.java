package pl.piomin.services.driver.subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import pl.piomin.services.driver.model.Driver;
import pl.piomin.services.driver.model.DriverStatus;
import pl.piomin.services.driver.model.Trip;
import pl.piomin.services.driver.model.TripStatus;
import pl.piomin.services.driver.repository.DriverRepository;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

@Service
public class DriverSubscriber implements MessageListener {

    private final Logger LOGGER = LoggerFactory.getLogger(DriverSubscriber.class);

    @Autowired
    DriverRepository repository;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] bytes) {
        Trip trip = mapper.readValue(message.getBody(), Trip.class);
        LOGGER.info("Message received: {}", trip.toString());
        Optional<Driver> optDriver = repository.findById(trip.getDriverId());
        if (optDriver.isPresent()) {
            Driver driver = optDriver.get();
            if (trip.getStatus() == TripStatus.DONE)
                driver.setStatus(DriverStatus.WAITING);
            else
                driver.setStatus(DriverStatus.BUSY);
            repository.save(driver);
        }
    }

}
