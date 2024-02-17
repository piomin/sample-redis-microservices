package pl.piomin.services.passenger.web;

import java.util.List;
import java.util.Optional;

import pl.piomin.services.passenger.model.Passenger;
import pl.piomin.services.passenger.repository.PassengerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    PassengerRepository repository;

    @PostMapping
    public Passenger add(Passenger passenger) {
        return repository.save(passenger);
    }

    @GetMapping("/{id}")
    public Passenger findById(@PathVariable("id") Long id) {
        Optional<Passenger> optPassenger = repository.findById(id);
        if (optPassenger.isPresent()) {
            return optPassenger.get();
        } else {
            return null;
        }
    }

    @GetMapping
    public List<Passenger> findAll() {
        return (List<Passenger>) repository.findAll();
    }

}
