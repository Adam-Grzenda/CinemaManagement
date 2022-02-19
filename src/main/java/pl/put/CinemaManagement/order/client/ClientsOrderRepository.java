package pl.put.CinemaManagement.order.client;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientsOrderRepository extends CrudRepository<ClientsOrder, Long> {
    List<ClientsOrder> getClientsOrderByClient(Client client);

    Optional<ClientsOrder> findClientsOrderByClientAndId(Client client, Long orderId);
}
