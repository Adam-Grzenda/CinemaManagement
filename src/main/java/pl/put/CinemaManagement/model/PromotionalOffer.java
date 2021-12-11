package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "promotional_offer")
public class PromotionalOffer extends CinemaEntity {
    @NotNull
    private String name;
    @NotNull
    private float discount;

    @ManyToMany
    @JoinTable(
            name = "PromotionalOffersCrossClientSegments",
            joinColumns = @JoinColumn(name = "promotional_offer_id"),
            inverseJoinColumns = @JoinColumn(name = "client_segment_id")
    )
    private List<ClientSegment> clientSegments;
}
