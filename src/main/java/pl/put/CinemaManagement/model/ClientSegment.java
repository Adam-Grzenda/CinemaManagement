package pl.put.CinemaManagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "client_segment")
public class ClientSegment extends CinemaEntity {
    private String name;

    @ManyToMany
    @JoinTable(
            name = "client_client_segment",
            joinColumns = @JoinColumn(name = "client_segment_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<Client> clients;

    @ManyToMany
    @JoinTable(
            name = "promo_offer_client_segment",
            joinColumns = @JoinColumn(name = "client_segment_id"),
            inverseJoinColumns = @JoinColumn(name = "promo_offer_id"))
    private List<PromoOffer> promoOffers;
}
