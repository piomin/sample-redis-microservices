package pl.piomin.services.trip.web;

import pl.piomin.services.trip.publish.TripPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trips")
public class TripController {

	@Autowired
	TripPublisher publisher;

	@GetMapping("/publish/{message}")
	public String publish(@PathVariable("message") String message) {
		publisher.publish(message);
		return message;
	}

}
