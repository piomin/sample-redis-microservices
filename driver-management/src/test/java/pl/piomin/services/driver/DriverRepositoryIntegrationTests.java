package pl.piomin.services.driver;

import org.junit.Test;
import org.junit.runner.RunWith;
import pl.piomin.services.driver.model.Driver;
import pl.piomin.services.driver.model.DriverStatus;
import pl.piomin.services.driver.repository.DriverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.geo.Point;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataRedisTest
public class DriverRepositoryIntegrationTests {

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
