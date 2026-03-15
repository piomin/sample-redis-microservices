package pl.piomin.services.driver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.redis.test.autoconfigure.DataRedisTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.geo.Point;
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
    @ServiceConnection
    static final GenericContainer redis = new GenericContainer("redis:latest")
            .withExposedPorts(6379);

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
