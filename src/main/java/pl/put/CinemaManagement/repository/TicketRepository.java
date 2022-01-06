package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
