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

    @ManyToMany
    @JoinTable(
            name = "promo_offer_client_segment",
            joinColumns = @JoinColumn(name = "promo_offer_id"),
            inverseJoinColumns = @JoinColumn(name = "client_segment_id")
    )
    private List<ClientSegment> clientSegments;
}
