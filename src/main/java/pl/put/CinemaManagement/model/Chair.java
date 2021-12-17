package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import pl.put.CinemaManagement.model.pkeys.ChairPK;
import pl.put.CinemaManagement.model.pkeys.CinemaHallPK;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "chair",
        uniqueConstraints =
        @UniqueConstraint(columnNames =
                {"hall_row", "hall_column", "cinema_hall_id"}))
public class Chair extends CinemaEntity {
    private enum ChairTypes {
        NORMAL,
        PREMIUM,
        DOUBLE
    }

    @Column(name = "hall_row")
    @NotNull
    private int hallRow;

    @Column(name = "hall_column")
    @NotNull
    private int hallColumn;

    @Column(name = "chair_type")
    @Enumerated(EnumType.STRING)
    private ChairTypes chairType;

    @JoinColumn(name = "cinema_hall_id",
            referencedColumnName = "id")
    @ManyToOne
    private CinemaHall cinemaHall;
}
