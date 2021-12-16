package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.put.CinemaManagement.model.pkeys.CinemaHallPK;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@IdClass(CinemaHallPK.class)
public class CinemaHall {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int number;

    private String type;

    @JoinColumn(name = "cinema", referencedColumnName = "id")
    @ManyToOne
    private Cinema cinema;

    @EmbeddedId
    protected CinemaHallPK cinemaHallPK;

    @OneToMany(mappedBy = "cinemaHall")
    private List<Chair> chairs;
}

