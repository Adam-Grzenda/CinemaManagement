package pl.put.CinemaManagement.order.controller;

import javassist.tools.web.BadHttpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.put.CinemaManagement.model.*;
import pl.put.CinemaManagement.order.dto.OrderDTO;
import pl.put.CinemaManagement.repository.ClientRepository;
import pl.put.CinemaManagement.repository.ClientsOrderRepository;
import pl.put.CinemaManagement.repository.FilmShowRepository;
import pl.put.CinemaManagement.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class OrderController {

    private final ClientsOrderRepository clientsOrderRepository;
    private final TicketRepository ticketRepository;
    private final ClientRepository clientRepository;
    private final FilmShowRepository filmShowRepository;

    @PostMapping(value = "/placeOrder")
    ClientsOrder placeOrder(@RequestBody OrderDTO orderDTO) throws BadHttpRequest {
        ClientsOrder clientsOrder = new ClientsOrder();

        Client client = clientRepository.findById(orderDTO.getClientId()).orElseThrow(BadHttpRequest::new);
        clientsOrder.setClient(client);

        FilmShow filmShow = filmShowRepository.findById(orderDTO.getFilmShowId()).orElseThrow(BadHttpRequest::new);

        List<Ticket> tickets = new ArrayList<>();

        log.info("Tickets: ");
        for (Chair chair: orderDTO.getChairs()) {
            Ticket ticket = new Ticket();
            ticket.setChair(chair);
            ticket.setFilmShow(filmShow);
            //TODO set promo offer
            log.info(ticket.toString());
        }

        //TODO assign tickets to order


        float orderCost = clientsOrder.calculateOrderCost();
        clientsOrder.setAmount(orderCost); //TODO - może status płatności, kwotę etc ustawiać procedurką?

        return clientsOrderRepository.save(clientsOrder);
    }
}
