package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.Advertisement;

import java.util.List;

public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {


}
