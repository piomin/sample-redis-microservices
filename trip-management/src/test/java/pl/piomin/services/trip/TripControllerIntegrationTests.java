package pl.piomin.services.trip;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.piomin.services.trip.model.Trip;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class TripControllerIntegrationTests {

	static BlockingQueue<Trip> queue = new LinkedBlockingQueue<>();

	@TestConfiguration
	static class TripTestConfiguration {
		@Autowired
		RedisConnectionFactory redisConnectionFactory;
		@Autowired
		ChannelTopic topic;

		@Bean
		RedisMessageListenerContainer container() {
			RedisMessageListenerContainer container = new RedisMessageListenerContainer();
			container.addMessageListener(messageListener(), topic);
			container.setConnectionFactory(redisConnectionFactory);
			return container;
		}

		@Bean
		MessageListenerAdapter messageListener() {
			return new MessageListenerAdapter(new TripTestListener(queue));
		}
	}

	@Container
	static final GenericContainer redis = new GenericContainer("redis:latest")
			.withExposedPorts(6379);

//	@DynamicPropertySource
//	static void redisProperties(DynamicPropertyRegistry registry) {
//		int port = redis.getFirstMappedPort();
//		registry.add("spring.redis.port", () -> port);
//	}

	@BeforeAll
	static void init() {
		System.setProperty("spring.redis.port", redis.getFirstMappedPort().toString());
	}

	@Autowired
	TestRestTemplate template;

	@Test
	public void testCreate() throws InterruptedException {
		Trip trip = new Trip();
		trip.setStartDate(new Date());
		trip.setDriverId(1L);
		trip.setPassengerId(1L);
		trip = template.postForObject("/trips", trip, Trip.class);
		assertNotNull(trip, "Trip null!");
		assertNotNull(trip.getId(), "Trip id null!");

		Trip t = queue.take();
		assertNotNull(t);
		assertEquals(t.getId(), trip.getId());
	}

}
