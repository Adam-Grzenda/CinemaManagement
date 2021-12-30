package pl.put.CinemaManagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "ticket",
        uniqueConstraints =
        @UniqueConstraint(columnNames =
                {"film_show_id", "chair_id"}))
public class Ticket extends CinemaEntity {

    @ManyToOne
    @JoinColumn(name = "clients_order_id",
            referencedColumnName = "id")
    private ClientsOrder clientsOrder;

    @ManyToOne
    @JoinColumn(name = "film_show_id",
            referencedColumnName = "id")
    private FilmShow filmShow;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "chair_id",
            referencedColumnName = "id")
    private Chair chair;

    @ManyToOne
    @JoinColumn(name = "promo_offer_id",
            referencedColumnName = "id")
    private PromoOffer promoOffer;

}
