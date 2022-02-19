package pl.put.CinemaManagement.order.client;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> getClientByExternalId(String externalId);
}

