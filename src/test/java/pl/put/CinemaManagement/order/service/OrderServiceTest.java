package pl.put.CinemaManagement.order.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import pl.put.CinemaManagement.model.*;
import pl.put.CinemaManagement.order.dto.Order;
import pl.put.CinemaManagement.repository.BasePriceRepository;
import pl.put.CinemaManagement.repository.ClientsOrderRepository;
import pl.put.CinemaManagement.repository.FilmShowRepository;
import pl.put.CinemaManagement.repository.PromoOfferRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.put.CinemaManagement.model.Chair.ChairTypes.NORMAL;
import static pl.put.CinemaManagement.model.ClientsOrder.PaymentStatus.OPEN;
import static pl.put.CinemaManagement.model.ClientsOrder.PaymentType.DEBT_CARD;

@Slf4j
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private ClientsOrderRepository orderRepository;

    @Mock
    private FilmShowRepository filmShowRepository;

    @Mock
    private BasePriceRepository priceRepository;

    @Mock
    private PromoOfferRepository promoOfferRepository;

    private OrderService orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.orderService = new OrderService(
                orderRepository,
                filmShowRepository,
                priceRepository,
                promoOfferRepository
        );
    }

    @Test
    void shouldPlaceValidOrder() {
        Long filmId = 123L;
        Long promoOfferId = 456L;
        Date filmShowDate = Date.valueOf(LocalDate.now());

        Cinema cinema = getCinema();

        Chair chair = cinema.getCinemaHalls().get(0).getChairs().get(0);
        FilmShow filmShow = new FilmShow();
        filmShow.setDate(filmShowDate);

        PromoOffer promoOffer = new PromoOffer();
        promoOffer.setDiscount(10.0f);

        Order order = new Order();
        order.setFoodOrderItems(List.of());
        order.setFilmShowId(filmId);
        order.setPromoOfferId(promoOfferId);

        order.setChairs(List.of(chair));
        order.setPaymentType(DEBT_CARD.name());
        order.setPaymentStatus(OPEN.name());

        when(filmShowRepository.findById(filmId)).thenReturn(
                Optional.of(filmShow)
        );

        when(promoOfferRepository.findById(promoOfferId)).thenReturn(
                Optional.of(promoOffer)
        );

        BasePrice basePrice = new BasePrice();
        basePrice.setBasePrice(10);
        basePrice.setItemType("Chair");
        basePrice.setItemSubtype(NORMAL.name());

        when(priceRepository.findByItemTypeAndItemSubtype("Chair", NORMAL.name())).thenReturn(
                Optional.of(basePrice)
        );

        when(orderRepository.save(any())).thenAnswer((Answer<ClientsOrder>) invocationOnMock -> invocationOnMock.getArgument(0));


        Client client = getClient();
        ClientsOrder clientsOrder = orderService.placeOrder(order, client);

        assertEquals(client.getExternalId(), clientsOrder.getClient().getExternalId());
        assertEquals(9f, clientsOrder.getAmount());
        assertEquals(OPEN.name(), clientsOrder.getPaymentStatus().name());
        assertEquals(DEBT_CARD, clientsOrder.getPaymentType());

        clientsOrder.getTickets().forEach(
                ticket -> {
                    assertTrue(ticket.getFilmShow().getDate().toLocalDate().isEqual(filmShowDate.toLocalDate()));
                    Chair ticketChair = ticket.getChair();
                    assertEquals(ticketChair.getHallColumn(), chair.getHallColumn());
                    assertEquals(ticketChair.getChairType(), chair.getChairType());
                }
        );

    }

    @Test
    void getOrdersForUser() {
    }

    @Test
    void updateOrderState() {
    }

    @Test
    void calculateOrderCost() {
    }

    @Test
    void realizeOrder() {
    }


    private Cinema getCinema() {
        Cinema cinema = new Cinema();

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCinema(cinema);

        Chair chair = new Chair();
        chair.setHallColumn(1);
        chair.setHallRow(1);
        chair.setChairType(NORMAL);
        chair.setCinemaHall(cinemaHall);

        cinemaHall.setChairs(List.of(chair));
        cinemaHall.setNumber(1);
        cinemaHall.setType("NORMAL");

        cinema.setCinemaHalls(List.of(cinemaHall));

        return cinema;
    }

    public Client getClient() {
        Client client = new Client();
        client.setClientSegments(List.of());
        client.setExternalId(UUID.randomUUID().toString());
        client.setName("John Smith");

        return client;
    }


}