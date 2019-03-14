package pl.piomin.services.driver.repository;

import pl.piomin.services.driver.model.Driver;

import org.springframework.data.repository.CrudRepository;

public interface DriverRepository extends CrudRepository<Driver, Long> {
}
