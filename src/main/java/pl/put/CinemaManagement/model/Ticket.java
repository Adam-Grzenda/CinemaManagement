package pl.put.CinemaManagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Ticket extends CinemaEntity{
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "order",
            referencedColumnName = "order_id")
    private ClientsOrder clientsOrder;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "client",
            referencedColumnName = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "promotional_offer",
            referencedColumnName = "id")
    private PromotionalOffer promotionalOffer;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "filmshow",
            referencedColumnName = "filmshow_id")
    private FilmShow filmShow;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "chair",
            referencedColumnName = "chair_id")
    private Chair chair;
}
