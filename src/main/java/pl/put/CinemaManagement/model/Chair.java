package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;


@Getter @Setter
@NoArgsConstructor
@Entity
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

    @ManyToOne
    @JoinColumn(name = "cinema_hall_id", nullable = false)
    private CinemaHall cinemaHall;
}
