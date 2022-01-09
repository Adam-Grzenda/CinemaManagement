package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.FoodCourt;

import java.util.List;

public interface FoodCourtRepository
        extends CrudRepository<FoodCourt, Long> {

    List<FoodCourt> getAllByCinema_Id(Long id);

}
