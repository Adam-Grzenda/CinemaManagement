package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import pl.put.CinemaManagement.model.pkeys.ChairPK;
import pl.put.CinemaManagement.model.pkeys.CinemaHallPK;

import javax.persistence.*;


@Getter @Setter
@NoArgsConstructor
@Entity
@IdClass(ChairPK.class)

public class Chair extends CinemaEntity{
    public enum ChairTypes {
        NORMAL,
        PREMIUM,
        DOUBLE
    }
    @NotNull
    private int hallRow;
    @NotNull
    private int hallColumn;
    @Enumerated(EnumType.STRING)
    private ChairTypes chairType;

    @JoinColumn(name = "cinemaHall", referencedColumnName = "number")
    @ManyToOne
    private CinemaHall cinemaHall;

    @EmbeddedId
    private ChairPK chairPK;
}
