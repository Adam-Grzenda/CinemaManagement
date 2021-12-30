package pl.put.CinemaManagement.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import pl.put.CinemaManagement.model.pkeys.FoodCourt_ProductTypePK;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(
        name = "food_court_product_type")
public class FoodCourt_ProductType {

    @EmbeddedId
    private FoodCourt_ProductTypePK id;

    @Column(name = "stored_units_count")
    @ColumnDefault(value = "0")
    private Long storedUnitsCount;

    @ManyToOne
    @MapsId("foodCourt")
    @JoinColumn(name = "food_court_id",
            referencedColumnName = "id")
    private FoodCourt foodCourt;

    @ManyToOne
    @MapsId("productType")
    @JoinColumn(name = "product_type_id",
            referencedColumnName = "id")
    private ProductType productType;


}
