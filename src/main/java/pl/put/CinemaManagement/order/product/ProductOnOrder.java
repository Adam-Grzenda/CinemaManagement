package pl.put.CinemaManagement.order.product;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.put.CinemaManagement.order.client.ClientsOrder;

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
public class ProductOnOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

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
