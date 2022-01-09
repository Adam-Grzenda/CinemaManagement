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


    @ManyToOne
    @JoinColumn(name = "promo_offer", referencedColumnName = "id")
    private PromoOffer promoOffer;
}
