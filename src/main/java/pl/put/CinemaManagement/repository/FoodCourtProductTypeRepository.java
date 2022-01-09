package pl.put.CinemaManagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.put.CinemaManagement.model.FoodCourts_ProductType;
import pl.put.CinemaManagement.model.ProductType;

import java.util.List;

public interface FoodCourtProductTypeRepository
        extends CrudRepository<FoodCourts_ProductType, Long> {

    @Query("FROM ProductType p WHERE p.id IN " +
            "(SELECT productType.id FROM FoodCourts_ProductType " +
            "WHERE foodCourt.id = :foodCourtId)")
    List<ProductType> findAllTypesForFoodCourt(@Param("foodCourtId") Long foodCourtId);

}
