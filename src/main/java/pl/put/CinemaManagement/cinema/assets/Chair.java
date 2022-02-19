package pl.put.CinemaManagement.cinema.assets;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
public class Chair {
    public enum ChairTypes {
        NORMAL,
        PREMIUM,
        DOUBLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "hall_row")
    @NotNull
    private int hallRow;

    @Column(name = "hall_column")
    @NotNull
    private int hallColumn;

    @Column(name = "chair_type")
    @ColumnDefault(value = "'NORMAL'")
    @Enumerated(EnumType.STRING)
    private ChairTypes chairType;

    @JoinColumn(name = "cinema_hall_id",
            referencedColumnName = "id")
    @ManyToOne
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CinemaHall cinemaHall;
}
