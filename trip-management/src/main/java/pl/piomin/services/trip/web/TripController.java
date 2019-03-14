package pl.piomin.services.trip.web;

import pl.piomin.services.trip.model.Trip;
import pl.piomin.services.trip.publish.TripPublisher;
import pl.piomin.services.trip.repository.TripRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trips")
public class TripController {

	@Autowired
	TripPublisher publisher;
	@Autowired
	TripRepository repository;

	@PostMapping
	public Trip create(@RequestBody Trip trip) {
		trip = repository.save(trip);
		publisher.publish(trip);
		return trip;
	}

}
