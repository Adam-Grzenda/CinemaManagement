package pl.put.CinemaManagement.model.pkeys;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

@Embeddable
public class ChairPK implements Serializable {
    protected Long id;
    @EmbeddedId
    protected CinemaHallPK cinemaHallPK;
}
