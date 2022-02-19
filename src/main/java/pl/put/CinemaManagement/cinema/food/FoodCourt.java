package pl.put.CinemaManagement.cinema.food;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.put.CinemaManagement.cinema.Cinema;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "food_court",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"name", "cinema_id"}))
public class FoodCourt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "checkout_number")
    @NotNull
    private int checkoutNumber;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    @OneToMany(mappedBy = "foodCourt")
    private List<FoodCourtProductType> foodCourtProductTypes;


}
