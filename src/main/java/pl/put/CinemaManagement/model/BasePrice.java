package pl.put.CinemaManagement.model;


import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.Column;

@Getter
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
