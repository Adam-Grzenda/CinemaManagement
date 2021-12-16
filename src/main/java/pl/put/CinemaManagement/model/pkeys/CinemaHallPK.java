package pl.put.CinemaManagement.model.pkeys;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CinemaHallPK implements Serializable {
    protected Long number;
    protected Long cinema;
}
