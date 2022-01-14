package pl.put.CinemaManagement.order.dto;

import lombok.Data;
import lombok.ToString;
import pl.put.CinemaManagement.model.ClientsOrder;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Data
public class OrderDisplay {
    private Date orderDate;
    private List<TicketDisplay> tickets;

    public static OrderDisplay fromClientsOrder(ClientsOrder clientsOrder) {
        OrderDisplay orderDisplay = new OrderDisplay();

        orderDisplay.setOrderDate(clientsOrder.getDate());

        List<TicketDisplay> tickets = clientsOrder
                .getTickets()
                .stream()
                .map(TicketDisplay::fromTicket)
                .collect(Collectors.toList());

        orderDisplay.setTickets(tickets);

        return orderDisplay;
    }
}
