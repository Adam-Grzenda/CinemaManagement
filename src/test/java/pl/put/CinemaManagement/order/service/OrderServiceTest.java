package pl.put.CinemaManagement.order.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import pl.put.CinemaManagement.model.*;
import pl.put.CinemaManagement.order.dto.Order;
import pl.put.CinemaManagement.order.exception.BadOrderException;
import pl.put.CinemaManagement.repository.BasePriceRepository;
import pl.put.CinemaManagement.repository.ClientsOrderRepository;
import pl.put.CinemaManagement.repository.FilmShowRepository;
import pl.put.CinemaManagement.repository.PromoOfferRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.put.CinemaManagement.model.Chair.ChairTypes.NORMAL;
import static pl.put.CinemaManagement.model.ClientsOrder.PaymentStatus.OPEN;
import static pl.put.CinemaManagement.model.ClientsOrder.PaymentType.DEBT_CARD;

@Slf4j
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private final Random random = new Random();

    @Mock
    private ClientsOrderRepository orderRepository;

    @Mock
    private FilmShowRepository filmShowRepository;

    @Mock
    private BasePriceRepository priceRepository;

    @Mock
    private PromoOfferRepository promoOfferRepository;

    private OrderService orderService;
    private Cinema cinema;

    private Long filmId;
    private Long promoOfferId;

    private BasePrice basePrice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.orderService = new OrderService(
                orderRepository,
                filmShowRepository,
                priceRepository,
                promoOfferRepository
        );

        this.cinema = createCinema();
        this.basePrice = createBasePrice();

        this.filmId = random.nextLong();
        this.promoOfferId = random.nextLong();
    }

    @Test
    void shouldPlaceValidOrder() {

        var client = createClient();
        var filmShow = createFilmShow();
        var promoOffer = createPromoOffer();
        var chairs = getChairs();

        Order order = new Order();
        order.setFilmShowId(filmId);
        order.setPromoOfferId(promoOfferId);
        order.setChairs(chairs);
        order.setPaymentType(DEBT_CARD.name());
        order.setPaymentStatus(OPEN.name());

        when(filmShowRepository.findById(filmId)).thenReturn(Optional.of(filmShow));
        when(promoOfferRepository.findById(promoOfferId)).thenReturn(Optional.of(promoOffer));
        when(priceRepository.findByItemTypeAndItemSubtype("Chair", NORMAL.name())).thenReturn(Optional.of(basePrice));
        when(orderRepository.save(any())).thenAnswer((Answer<ClientsOrder>) invocationOnMock -> invocationOnMock.getArgument(0));

        var clientsOrder = orderService.placeOrder(order, client);

        assertEquals(client.getExternalId(), clientsOrder.getClient().getExternalId());
        assertEquals(9f, clientsOrder.getAmount());
        assertEquals(OPEN.name(), clientsOrder.getPaymentStatus().name());
        assertEquals(DEBT_CARD, clientsOrder.getPaymentType());

        var tickets = clientsOrder.getTickets();

        chairs.forEach(
                expectedChair -> assertTrue(tickets.stream().anyMatch(
                        ticket ->
                                ticket.getFilmShow().getDate().toLocalDate().isEqual(filmShow.getDate().toLocalDate())
                                        &&
                                        ticket.getChair().getHallColumn() == expectedChair.getHallColumn()
                                        &&
                                        ticket.getChair().getChairType() == expectedChair.getChairType()

                ))
        );

    }

    @Test
    void shouldNotPlaceOrderWithInvalidFilm() {
        var client = createClient();
        var chairs = getChairs();

        Order order = new Order();
        order.setPromoOfferId(promoOfferId);
        order.setChairs(chairs);
        order.setPaymentType(DEBT_CARD.name());
        order.setPaymentStatus(OPEN.name());

        when(filmShowRepository.findById(any())).thenReturn(Optional.empty());

        Assert.assertThrows("Invalid film show id", BadOrderException.class, () -> orderService.placeOrder(order, client));
    }

    @Test
    void shouldNotPlaceOrderWithInvalidPromoOffer() {
        var client = createClient();
        var chairs = getChairs();
        var filmShow = createFilmShow();

        Order order = new Order();
        order.setPromoOfferId(promoOfferId);
        order.setChairs(chairs);
        order.setPaymentType(DEBT_CARD.name());
        order.setPaymentStatus(OPEN.name());

        when(filmShowRepository.findById(any())).thenReturn(Optional.of(filmShow));
        when(promoOfferRepository.findById(any())).thenReturn(Optional.empty());

        Assert.assertThrows("Invalid promo offer id", BadOrderException.class, () -> orderService.placeOrder(order, client));
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


    private Cinema createCinema() {
        Cinema cinema = new Cinema();

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCinema(cinema);

        Chair chair = new Chair();
        chair.setHallColumn(1);
        chair.setHallRow(1);
        chair.setChairType(NORMAL);
        chair.setCinemaHall(cinemaHall);

        Chair chair2 = new Chair();
        chair2.setHallColumn(2);
        chair2.setHallRow(2);
        chair2.setChairType(NORMAL);
        chair2.setCinemaHall(cinemaHall);


        cinemaHall.setChairs(List.of(chair, chair2));
        cinemaHall.setNumber(2);
        cinemaHall.setType("NORMAL");

        cinema.setCinemaHalls(List.of(cinemaHall));

        return cinema;
    }

    public Client createClient() {
        Client client = new Client();
        client.setClientSegments(List.of());
        client.setExternalId(UUID.randomUUID().toString());
        client.setName("John Smith");

        return client;
    }

    private FilmShow createFilmShow() {
        FilmShow filmShow = new FilmShow();
        filmShow.setDate(Date.valueOf(LocalDate.now()));
        return filmShow;
    }

    private PromoOffer createPromoOffer() {
        PromoOffer promoOffer = new PromoOffer();
        promoOffer.setDiscount(10.0f);
        return promoOffer;
    }

    private BasePrice createBasePrice() {
        BasePrice basePrice = new BasePrice();
        basePrice.setBasePrice(10);
        basePrice.setItemType("Chair");
        basePrice.setItemSubtype(NORMAL.name());
        return basePrice;
    }

    private List<Chair> getChairs() {
        return cinema.getCinemaHalls().get(0).getChairs();
    }
}
