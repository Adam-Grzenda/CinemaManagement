package pl.put.CinemaManagement.order.client;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.put.CinemaManagement.order.promo.PromoOffer;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "client_segment",
        uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class ClientSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name")
    @NotNull
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
