package pl.put.CinemaManagement.order.dto;

import lombok.Data;
import lombok.ToString;
import pl.put.CinemaManagement.model.Chair;
import pl.put.CinemaManagement.model.FilmShow;
import pl.put.CinemaManagement.model.Ticket;

import java.sql.Date;

@ToString
@Data
public class TicketDisplay {
    private Integer hallRow;
    private Integer hallColumn;
    private Float discount;
    private float finalPrice;
    private String filmShowTitle;
    private Date filmShowDate;


    public static TicketDisplay fromTicket(Ticket ticket) {
        TicketDisplay ticketDisplay = new TicketDisplay();
        Chair chair = ticket.getChair();

        ticketDisplay.setHallColumn(chair.getHallColumn());
        ticketDisplay.setHallRow(chair.getHallRow());
        ticketDisplay.setDiscount(ticket.getPromoOffer().getDiscount());

        FilmShow filmShow = ticket.getFilmShow();

        ticketDisplay.setFilmShowTitle(filmShow.getFilm().getTitle());
        ticketDisplay.setFilmShowDate(filmShow.getDate());
        ticketDisplay.setFinalPrice(ticket.getPrice());

        return ticketDisplay;
    }
}
