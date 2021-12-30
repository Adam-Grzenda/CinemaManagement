package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.FoodCourt;

public interface FoodCourtRepository
        extends CrudRepository<FoodCourt, Long> {

}
