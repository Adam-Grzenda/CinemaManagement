package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "product_type" )
public class ProductType extends CinemaEntity {

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "unit")
    @NotNull
    private String unit;

    @Column(name = "count")
    @ColumnDefault(value = "0")
    @NotNull
    private float count;

    @OneToMany(mappedBy = "productType")
    private List<FoodCourt_ProductType> foodCourtProductTypes;

}
