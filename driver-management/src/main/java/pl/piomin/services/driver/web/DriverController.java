package pl.piomin.services.driver.web;

import java.util.List;
import java.util.Optional;

import pl.piomin.services.driver.model.Driver;
import pl.piomin.services.driver.repository.DriverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    DriverRepository repository;

    @PostMapping
    public Driver add(Driver driver) {
        return repository.save(driver);
    }

    @GetMapping("/{id}")
    public Driver findById(@PathVariable("id") Long id) {
        Optional<Driver> optDriver = repository.findById(id);
        if (optDriver.isPresent()) {
            return optDriver.get();
        } else {
            return null;
        }
    }

    @GetMapping
    public List<Driver> findAll() {
        return (List<Driver>) repository.findAll();
    }

}
