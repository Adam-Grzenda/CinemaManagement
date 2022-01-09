package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.ProductType;

import java.util.List;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {

}
