package pl.put.CinemaManagement.cinema;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.put.CinemaManagement.cinema.assets.CinemaHall;
import pl.put.CinemaManagement.cinema.food.FoodCourt;
import pl.put.CinemaManagement.CinemaEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
public class Cinema extends CinemaEntity {
    @NotNull
    private String name;
    @NotNull
    private String address;

    @OneToMany(mappedBy = "cinema")
    private List<CinemaHall> cinemaHalls;

    @OneToMany(mappedBy = "cinema")
    private List<FoodCourt> foodCourts;
}