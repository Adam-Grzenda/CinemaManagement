package pl.put.CinemaManagement.cinema;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.cinema.Cinema;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {

    Cinema findCinemaByName(String name);

}
