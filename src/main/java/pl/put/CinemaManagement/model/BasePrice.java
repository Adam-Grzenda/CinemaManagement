package pl.put.CinemaManagement.model;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(
        name = "prices")
public class BasePrice extends CinemaEntity {

    @NotNull
    @Column(name = "item_name")
    String itemType;

    @Column(name = "item_subtype")
    String itemSubtype;

    @NotNull
    @Column(name = "base_price")
    float basePrice;
}
