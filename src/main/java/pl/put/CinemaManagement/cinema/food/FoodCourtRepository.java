package pl.put.CinemaManagement.cinema.food;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodCourtRepository
        extends CrudRepository<FoodCourt, Long> {

    List<FoodCourt> getAllByCinema_Id(Long id);

}
