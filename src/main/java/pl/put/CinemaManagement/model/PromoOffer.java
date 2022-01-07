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
        name = "promo_offer")
public class PromoOffer extends CinemaEntity {

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "discount")
    @ColumnDefault(value = "0")
    @NotNull
    private float discount;


    @OneToMany(mappedBy = "promoOffer")
    private List<ClientSegment> clientSegments;
}
