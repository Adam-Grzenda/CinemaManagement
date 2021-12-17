package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "product_on_order")
public class ProductOnOrder extends CinemaEntity {

    @Column(name = "count")
    @NotNull
    private int count;

    @ManyToOne
    @JoinColumn(name = "product_type_id",
            referencedColumnName = "id")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "order_id",
            referencedColumnName = "id")
    private ClientsOrder clientsOrder;
}
