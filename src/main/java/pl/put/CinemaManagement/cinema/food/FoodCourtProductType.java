package pl.put.CinemaManagement.cinema.food;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import pl.put.CinemaManagement.order.product.ProductType;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "food_court_product_type")
public class FoodCourtProductType {

    @EmbeddedId
    private FoodCourtProductTypePK id;

    @Column(name = "stored_units_count")
    @ColumnDefault(value = "0")
    @NotNull
    private Long storedUnitsCount;

    @ManyToOne
    @MapsId("foodCourt")
    @NotNull
    @JoinColumn(name = "food_court_id",
            referencedColumnName = "id")
    private FoodCourt foodCourt;

    @ManyToOne
    @MapsId("productType")
    @NotNull
    @JoinColumn(name = "product_type_id",
            referencedColumnName = "id")
    private ProductType productType;


}
