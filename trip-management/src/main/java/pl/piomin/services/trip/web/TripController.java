package pl.piomin.services.trip.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.trip.model.Trip;
import pl.piomin.services.trip.publish.TripPublisher;
import pl.piomin.services.trip.repository.TripRepository;

import java.util.List;
import java.util.Optional;

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


    @GetMapping("/{id}")
    public Trip findById(@PathVariable Long id) {
        Optional<Trip> optTrip = repository.findById(id);
        if (optTrip.isPresent()) {
            return optTrip.get();
        } else {
            return null;
        }
    }

    @GetMapping
    public List<Trip> findAll() {
        return (List<Trip>) repository.findAll();
    }

}
