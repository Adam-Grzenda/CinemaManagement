package pl.put.CinemaManagement.order.product;


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
        name = "prices")
public class BasePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    @Column(name = "item_name")
    String itemType;

    @Column(name = "item_subtype")
    String itemSubtype;

    @NotNull
    @Column(name = "base_price")
    float basePrice;
}
