package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class Ticket extends CinemaEntity {

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
