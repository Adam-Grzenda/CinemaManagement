package pl.put.CinemaManagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.PromoOffer;

import java.util.List;

public interface PromoOfferRepository extends CrudRepository<PromoOffer, Long> {

    PromoOffer findPromoOfferByName(String name);

    @Query("from PromoOffer p where p.id = any " +
            "(select s.promoOffer.id from ClientSegment s where " +
            "s.id= any (select cs.id from ClientSegment cs join cs.clients cl where " +
            "cl.id=any (select c.id from Client c where c.externalId = :clientId)))")
    List<PromoOffer> findAllForClient(String clientId);


}
