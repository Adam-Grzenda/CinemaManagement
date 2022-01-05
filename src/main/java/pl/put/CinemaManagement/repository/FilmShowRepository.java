package pl.put.CinemaManagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.CinemaManagement.model.FilmShow;

import java.util.List;

public interface FilmShowRepository extends CrudRepository<FilmShow, Long> {
    List<FilmShow> findFilmShowsByFilmId(Long filmId);
}
