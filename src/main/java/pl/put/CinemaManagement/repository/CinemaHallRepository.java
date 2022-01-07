package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.CinemaHall;

import java.util.List;

public interface CinemaHallRepository extends CrudRepository<CinemaHall, Long> {

    List<CinemaHall> findCinemaHallsByCinema_Name(String name);

    List<CinemaHall> findCinemaHallsByCinema_Id(Long id);
}
