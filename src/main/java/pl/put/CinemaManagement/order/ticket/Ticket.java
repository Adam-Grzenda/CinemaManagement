package pl.put.CinemaManagement.order.ticket;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.put.CinemaManagement.cinema.assets.Chair;
import pl.put.CinemaManagement.cinema.film.show.FilmShow;
import pl.put.CinemaManagement.order.client.ClientsOrder;
import pl.put.CinemaManagement.order.promo.PromoOffer;

import javax.persistence.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "ticket",
        uniqueConstraints =
        @UniqueConstraint(columnNames =
                {"film_show_id", "chair_id"}))
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "price")
    private float price;

    @ManyToOne
    @NotNull //#TODO czy na pewno?
    @JoinColumn(name = "clients_order_id",
            referencedColumnName = "id")
    private ClientsOrder clientsOrder;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "film_show_id",
            referencedColumnName = "id")
    private FilmShow filmShow;

    @PrimaryKeyJoinColumn(name = "chair_id",
            referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private Chair chair;

    @ManyToOne
    @JoinColumn(name = "promo_offer_id",
            referencedColumnName = "id")
    private PromoOffer promoOffer;

}
