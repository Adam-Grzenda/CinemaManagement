package pl.put.CinemaManagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ClientSegment extends CinemaEntity {
    private String name;

    @ManyToMany
    @JoinTable(
            name = "ClientsCrossClientSegments",
            joinColumns = @JoinColumn(name = "client_segment_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> clients;

    @ManyToMany
    @JoinTable(
            name = "PromotionalOffersCrossClientSegments",
            joinColumns = @JoinColumn(name = "client_segment_id"),
            inverseJoinColumns = @JoinColumn(name = "promotional_offer_id")
    )
    private List<PromotionalOffer> promotionalOffers;
}
