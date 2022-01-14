package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.Client;
import pl.put.CinemaManagement.model.ClientsOrder;

import java.util.List;
import java.util.Optional;

public interface ClientsOrderRepository extends CrudRepository<ClientsOrder, Long> {
    List<ClientsOrder> getClientsOrderByClient(Client client);

    Optional<ClientsOrder> findClientsOrderByClientAndId(Client client, Long orderId);
}
