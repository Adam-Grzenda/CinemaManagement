package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
