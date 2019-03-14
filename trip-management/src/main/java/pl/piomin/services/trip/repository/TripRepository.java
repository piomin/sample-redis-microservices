package pl.piomin.services.trip.repository;

import pl.piomin.services.trip.model.Trip;

import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long> {
}
