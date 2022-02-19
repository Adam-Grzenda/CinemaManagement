package pl.put.CinemaManagement.cinema.assets;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.cinema.assets.CinemaHall;

import java.util.List;

public interface CinemaHallRepository extends CrudRepository<CinemaHall, Long> {

    List<CinemaHall> findCinemaHallsByCinema_Name(String name);

    List<CinemaHall> findAllByCinema_Id(Long cinemaId);
}
