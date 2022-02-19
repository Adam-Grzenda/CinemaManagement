package pl.put.CinemaManagement.order.product;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.put.CinemaManagement.order.client.ClientsOrder;
import pl.put.CinemaManagement.CinemaEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "product_on_order",
        uniqueConstraints =
        @UniqueConstraint(columnNames =
                {"order_id", "product_type_id"}))
public class ProductOnOrder extends CinemaEntity {

    @Column(name = "count")
    @NotNull
    private int count;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "product_type_id",
            referencedColumnName = "id")
    private ProductType productType;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "order_id",
            referencedColumnName = "id")
    private ClientsOrder clientsOrder;
}
