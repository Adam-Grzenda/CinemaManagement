package pl.put.CinemaManagement.order.product;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.order.product.BasePrice;

import java.util.List;
import java.util.Optional;

public interface BasePriceRepository extends CrudRepository<BasePrice, Long> {
    Optional<BasePrice> findByItemTypeAndItemSubtype(String type, String subtype);

    List<BasePrice> findByItemType(String type);
}
