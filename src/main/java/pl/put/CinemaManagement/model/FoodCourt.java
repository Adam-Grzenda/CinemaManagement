package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class FoodCourt extends CinemaEntity{
    @NotNull
    private int checkoutCount;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    @ManyToMany
    @JoinTable(name = "FoodCourtsCrossProductTypes",
            joinColumns = @JoinColumn(name = "foodcourt_id"),
            inverseJoinColumns = @JoinColumn(name = "foodcourt_product_type_id"))
    private List<FoodCourtProductType> foodCourtProductTypes;
}
