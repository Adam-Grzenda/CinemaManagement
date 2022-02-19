package pl.put.CinemaManagement.cinema.film.show;

import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface FilmShowRepository extends CrudRepository<FilmShow, Long> {
    List<FilmShow> findFilmShowsByFilmId(Long filmId);

    List<FilmShow> findFilmShowsByDateBetweenAndFilmId(Date dateFrom, Date dateTo, Long filmId);

    List<FilmShow> findAllByCinemaHall_CinemaId(Long cinemaId);
}
