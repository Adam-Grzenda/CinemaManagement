package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.Cinema;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {
}
