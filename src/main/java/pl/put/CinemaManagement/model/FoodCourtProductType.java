package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FoodCourtProductType extends CinemaEntity{
    @NotNull
    private String name;
    @NotNull
    private String unit;
    @NotNull
    private float count;
    @ColumnDefault("0")
    private int storedUnitsCount;

    @ManyToMany
    @JoinTable(name = "FoodCourtsCrossProductTypes",
            joinColumns = @JoinColumn(name = "foodcourt_product_type_id"),
            inverseJoinColumns = @JoinColumn(name = "foodcourt_id"))
    private List<FoodCourt> foodCourts;
}
