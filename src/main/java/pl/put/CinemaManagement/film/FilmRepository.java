package pl.put.CinemaManagement.film;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.put.CinemaManagement.model.Film;

@RepositoryRestResource(exported = false)
public interface FilmRepository extends CrudRepository<Film, Long> {
    Film getFilmByTitle(String title);
}
