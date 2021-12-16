package pl.put.CinemaManagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @ManyToOne
    @MapsId("foodCourt")
    @JoinColumn(name = "food_court_id")
    private FoodCourt foodCourt;

    @ManyToOne
    @MapsId("productType")
    @JoinColumn(name = "product_type_id")
    private ProductType productType;


}
