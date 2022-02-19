package pl.put.CinemaManagement.cinema.film;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface FilmRepository extends CrudRepository<Film, Long> {
    Film getFilmByTitle(String title);
}
