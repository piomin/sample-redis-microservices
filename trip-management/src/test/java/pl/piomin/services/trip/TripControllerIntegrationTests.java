package pl.piomin.services.trip;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.util.Assert;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.piomin.services.trip.model.Trip;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class TripControllerIntegrationTests {

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
	public void testCreate() {
		Trip trip = new Trip();
		trip.setStartDate(new Date());
		trip.setDriverId(1L);
		trip.setPassengerId(1L);
		trip = template.postForObject("/trips", trip, Trip.class);
		Assert.notNull(trip, "Trip null!");
		Assert.notNull(trip.getId(), "Trip id null!");
	}

}
