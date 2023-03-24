package pl.piomin.services.driver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.geo.Point;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.piomin.services.driver.model.Driver;
import pl.piomin.services.driver.model.DriverStatus;
import pl.piomin.services.driver.repository.DriverRepository;

@DataRedisTest
@Testcontainers
public class DriverRepositoryIntegrationTests {

	@Container
	static final GenericContainer redis = new GenericContainer("redis:latest")
			.withExposedPorts(6379);

	@DynamicPropertySource
	static void redisProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.redis.port", redis::getFirstMappedPort);
	}

	@Autowired
	DriverRepository repository;

	@Test
	public void testAdd() {
		Driver driver = new Driver();
		driver.setName("John Smith");
		driver.setStatus(DriverStatus.WAITING);
		driver.setLocation(new Point(10, 20));
		repository.save(driver);
	}
}
