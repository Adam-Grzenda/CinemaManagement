package pl.put.CinemaManagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.put.CinemaManagement.model.Chair;

import java.util.List;

public interface ChairRepository extends CrudRepository<Chair, Long> {
    @Query("FROM Chair c1 WHERE c1.cinemaHall.id = (SELECT f.cinemaHall.id FROM FilmShow f WHERE f.id = :filmShowId) AND" +
            " c1.id NOT in (select c.id from Chair c inner join Ticket t on c.id = t.chair.id where t.filmShow.id = :filmShowId) ")
    List<Chair> findFreeChairsForShow(@Param("filmShowId") Long filmShowId);

}
