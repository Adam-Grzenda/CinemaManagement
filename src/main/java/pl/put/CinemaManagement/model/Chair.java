package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
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
    public enum ChairTypes {
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
