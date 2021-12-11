package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.Cinema;

import java.util.List;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {

    List<Cinema> findCinemaByName(String name);
}
