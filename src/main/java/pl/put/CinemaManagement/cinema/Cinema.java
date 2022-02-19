package pl.put.CinemaManagement.cinema;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.put.CinemaManagement.cinema.assets.CinemaHall;
import pl.put.CinemaManagement.cinema.food.FoodCourt;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "cinema",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "address")})
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    private String name;
    @NotNull
    private String address;

    @OneToMany(mappedBy = "cinema")
    private List<CinemaHall> cinemaHalls;

    @OneToMany(mappedBy = "cinema")
    private List<FoodCourt> foodCourts;
}
