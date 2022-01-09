package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.ClientsOrder;

public interface ClientsOrderRepository extends CrudRepository<ClientsOrder, Long> {
}
