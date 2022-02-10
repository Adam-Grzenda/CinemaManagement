package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
public class CinemaHall extends CinemaEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
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

