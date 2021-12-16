package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.query.Procedure;
import pl.put.CinemaManagement.model.pkeys.CinemaHallPK;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(
        name = "cinema_hall",
        uniqueConstraints=
            @UniqueConstraint(columnNames = {"number", "cinema"}))
public class CinemaHall extends CinemaEntity {
    private enum HallTypes {

    }
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private int number;

    private String type;

    @JoinColumn(name = "cinema", referencedColumnName = "id")
    @ManyToOne
    private Cinema cinema;

    @OneToMany(mappedBy = "cinemaHall")
    private List<Chair> chairs;
}

