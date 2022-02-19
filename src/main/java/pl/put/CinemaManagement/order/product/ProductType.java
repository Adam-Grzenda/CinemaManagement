package pl.put.CinemaManagement.order.product;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import pl.put.CinemaManagement.cinema.food.FoodCourtProductType;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "product_type",
        uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "unit")
    @NotNull
    private String unit;

    @Column(name = "amount")
    @ColumnDefault(value = "0")
    @NotNull
    private float amount;

    @OneToMany(mappedBy = "productType")
    private List<FoodCourtProductType> foodCourtProductTypes;

}
