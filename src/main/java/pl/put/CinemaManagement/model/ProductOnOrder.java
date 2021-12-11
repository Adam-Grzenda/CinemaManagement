package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductOnOrder extends CinemaEntity{
    @NotNull
    private int count;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "foodcourt_product_type",
            referencedColumnName = "foodcourt_product_type_id")
    private FoodCourtProductType foodCourtProductType;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "order",
            referencedColumnName = "order_id")
    private ClientsOrder clientsOrder;
}
