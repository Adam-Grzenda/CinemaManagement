package pl.put.CinemaManagement.order.promo;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import pl.put.CinemaManagement.order.client.ClientSegment;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "promo_offer",
        uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class PromoOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

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
