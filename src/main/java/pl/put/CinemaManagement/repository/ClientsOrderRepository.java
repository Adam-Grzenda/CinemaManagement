package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.Client;
import pl.put.CinemaManagement.model.ClientsOrder;

import java.util.List;

public interface ClientsOrderRepository extends CrudRepository<ClientsOrder, Long> {
    List<ClientsOrder> getClientsOrderByClient(Client client);
}
