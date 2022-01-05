package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.ProductType;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {
}
