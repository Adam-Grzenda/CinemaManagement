package pl.put.CinemaManagement.cinema.assets;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.put.CinemaManagement.cinema.Cinema;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "cinema_hall",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"number", "cinema_id"}))
public class CinemaHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int number;

    private String type;


    @JoinColumn(name = "cinema_id",
            referencedColumnName = "id")
    @ManyToOne
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cinema cinema;

    @OneToMany(mappedBy = "cinemaHall")
    private List<Chair> chairs;
}

