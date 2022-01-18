package pl.put.CinemaManagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.put.CinemaManagement.model.Chair;

import java.util.List;

public interface ChairRepository extends CrudRepository<Chair, Long> {
    @Query(nativeQuery = true, value="Select * FROM get_available_chairs(:filmShowId)")
    List<Chair> findFreeChairsForShow(@Param("filmShowId") Long filmShowId);

    @Query(nativeQuery = true, value="call ADD_CHAIRS(:rowNum, :colNum, :hallId)")
    void addChairs(Integer rowNum, Integer colNum, Integer hallId);

}
