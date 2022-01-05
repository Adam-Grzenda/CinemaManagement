package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.Film;


public interface FilmRepository extends CrudRepository<Film, Long> {
}
