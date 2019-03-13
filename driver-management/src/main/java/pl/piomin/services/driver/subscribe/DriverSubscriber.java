package pl.piomin.services.driver.subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class DriverSubscriber implements MessageListener {

	private final Logger LOGGER = LoggerFactory.getLogger(DriverSubscriber.class);

	@Override
	public void onMessage(Message message, byte[] bytes) {
		LOGGER.info("Listening...");
		LOGGER.info("Message received: {}", message.toString());
	}

}
