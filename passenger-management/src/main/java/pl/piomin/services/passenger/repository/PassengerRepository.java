package pl.piomin.services.passenger.repository;

import pl.piomin.services.passenger.model.Passenger;

import org.springframework.data.repository.CrudRepository;

public interface PassengerRepository extends CrudRepository<Passenger, Long> {
}
