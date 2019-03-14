package pl.piomin.services.trip;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import pl.piomin.services.trip.model.Trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TripControllerIntegrationTests {

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
