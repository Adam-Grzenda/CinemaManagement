package pl.put.CinemaManagement.order.dto;

import lombok.Data;
import lombok.ToString;
import pl.put.CinemaManagement.model.ClientsOrder;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Data
public class OrderDisplay {
    private Long orderID;
    private Timestamp orderDate;
    private List<TicketDisplay> tickets;
    private ClientsOrder.PaymentStatus paymentStatus;
    private Double price;

    public static OrderDisplay fromClientsOrder(ClientsOrder clientsOrder) {
        OrderDisplay orderDisplay = new OrderDisplay();

        orderDisplay.setOrderID(clientsOrder.getId());
        orderDisplay.setOrderDate(clientsOrder.getDate());
        orderDisplay.setPaymentStatus(clientsOrder.getPaymentStatus());
        orderDisplay.setPrice(clientsOrder.getAmount());

        List<TicketDisplay> tickets = clientsOrder
                .getTickets()
                .stream()
                .map(TicketDisplay::fromTicket)
                .collect(Collectors.toList());

        orderDisplay.setTickets(tickets);

        return orderDisplay;
    }
}
