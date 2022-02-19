package pl.put.CinemaManagement.cinema.food;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.put.CinemaManagement.order.product.ProductType;

import java.util.List;

public interface FoodCourtProductTypeRepository
        extends CrudRepository<FoodCourtProductType, Long> {

    @Query("FROM ProductType p WHERE p.id IN " +
            "(SELECT productType.id FROM FoodCourtProductType " +
            "WHERE foodCourt.id = :foodCourtId)")
    List<ProductType> findAllTypesForFoodCourt(@Param("foodCourtId") Long foodCourtId);

}
