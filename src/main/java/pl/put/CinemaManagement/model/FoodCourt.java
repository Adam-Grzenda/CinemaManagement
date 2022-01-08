package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "food_court"
)
public class FoodCourt extends CinemaEntity {

    @Column(name = "checkout_number")
    @NotNull
    private int checkoutNumber;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    @OneToMany(mappedBy = "foodCourt")
    private List<FoodCourts_ProductType> foodCourtProductTypes;


}
