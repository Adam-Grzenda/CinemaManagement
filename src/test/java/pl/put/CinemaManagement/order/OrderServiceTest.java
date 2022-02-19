package pl.put.CinemaManagement.order;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import pl.put.CinemaManagement.cinema.Cinema;
import pl.put.CinemaManagement.cinema.assets.Chair;
import pl.put.CinemaManagement.cinema.assets.CinemaHall;
import pl.put.CinemaManagement.cinema.film.Film;
import pl.put.CinemaManagement.cinema.film.show.FilmShow;
import pl.put.CinemaManagement.cinema.film.show.FilmShowRepository;
import pl.put.CinemaManagement.order.client.Client;
import pl.put.CinemaManagement.order.client.ClientsOrder;
import pl.put.CinemaManagement.order.client.ClientsOrderRepository;
import pl.put.CinemaManagement.order.dto.FoodOrderItem;
import pl.put.CinemaManagement.order.dto.Order;
import pl.put.CinemaManagement.order.product.BasePrice;
import pl.put.CinemaManagement.order.product.BasePriceRepository;
import pl.put.CinemaManagement.order.promo.PromoOffer;
import pl.put.CinemaManagement.order.promo.PromoOfferRepository;
import pl.put.CinemaManagement.order.ticket.Ticket;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.put.CinemaManagement.cinema.assets.Chair.ChairTypes.NORMAL;
import static pl.put.CinemaManagement.order.client.ClientsOrder.PaymentStatus.IN_PROCESS;
import static pl.put.CinemaManagement.order.client.ClientsOrder.PaymentStatus.OPEN;
import static pl.put.CinemaManagement.order.client.ClientsOrder.PaymentType.DEBT_CARD;

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

    private BasePrice baseChairPrice;
    private BasePrice baseFoodPrice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.orderService = new OrderService(
                orderRepository,
                filmShowRepository,
                priceRepository,
                promoOfferRepository
        );

        this.cinema = createCinema();
        this.baseChairPrice = createBasePrice("Chair", NORMAL.name());
        this.baseFoodPrice = createBasePrice("Food", NORMAL.name());

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
        when(priceRepository.findByItemTypeAndItemSubtype("Chair", NORMAL.name())).thenReturn(Optional.of(baseChairPrice));
        when(orderRepository.save(any())).thenAnswer((Answer<ClientsOrder>) invocationOnMock -> invocationOnMock.getArgument(0));

        var clientsOrder = orderService.placeOrder(order, client);

        assertEquals(client.getExternalId(), clientsOrder.getClient().getExternalId());
        assertEquals(18f, clientsOrder.getAmount());
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

        var exception = assertThrows(InvalidOrderException.class, () -> orderService.placeOrder(order, client));
        assertEquals("Invalid film show id", exception.getMessage());
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

        var exception = assertThrows(InvalidOrderException.class, () -> orderService.placeOrder(order, client));
        assertEquals(exception.getMessage(), "Invalid promo offer id");
    }

    @SneakyThrows
    @Test
    void getOrdersForUser() {
        var client = createClient();
        var chairs = getChairs();
        var filmShow = createFilmShow();

        List<Ticket> tickets = createTickets(chairs, filmShow);
        List<ClientsOrder> orders = new ArrayList<>();
        List<String> orderTimestamps = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Timestamp orderTimestamp = Timestamp.valueOf(LocalDateTime.of(2021, random.nextInt(1, 10), 10, 10, 12));
            orderTimestamps.add(orderTimestamp.toString());
            orders.add(createOrder(client, tickets, orderTimestamp));
        }

        when(orderRepository.getClientsOrderByClient(client)).thenReturn(orders);

        var orderDisplays = orderService.getOrdersForUser(client);

        var displayTimestamps = orderDisplays.stream().map(a -> a.getOrderDate().toString()).toList();
        assertTrue(orderTimestamps.containsAll(displayTimestamps));

        var expectedPriceSum = tickets.stream().mapToDouble(Ticket::getPrice).sum();
        assertTrue(orderDisplays.stream().allMatch(a -> a.getPrice() == expectedPriceSum && a.getPaymentStatus() == IN_PROCESS));

    }

    private ClientsOrder createOrder(Client client, List<Ticket> tickets, Timestamp date) {
        ClientsOrder order = new ClientsOrder();
        order.setTickets(tickets);
        order.setPaymentStatus(IN_PROCESS);
        order.setClient(client);
        order.setAmount(tickets.stream().mapToDouble(Ticket::getPrice).sum());
        order.setDate(date);

        return order;
    }

    @Test
    void shouldCalculateOrderCostWithValidPromoOffer() {
        var promoOffer = createPromoOffer();
        var chairs = getChairs();
        var foods = createFoodItems(5);


        Order order = new Order();
        order.setPromoOfferId(promoOfferId);
        order.setFoodOrderItems(foods);
        order.setChairs(chairs);

        when(promoOfferRepository.findById(promoOfferId)).thenReturn(Optional.of(promoOffer));
        when(priceRepository.findByItemTypeAndItemSubtype("Chair", NORMAL.name())).thenReturn(Optional.of(baseChairPrice));
        when(priceRepository.findByItemTypeAndItemSubtype("Food", NORMAL.name())).thenReturn(Optional.of(baseFoodPrice));

        var costs = orderService.calculateOrderCost(order);

        double expectedTotal = chairs.size() * baseChairPrice.getBasePrice() * (100 - promoOffer.getDiscount()) / 100 + foods.size() * baseFoodPrice.getBasePrice() * (100 - promoOffer.getDiscount()) / 100;
        assertEquals(expectedTotal, costs.stream().mapToDouble(orderProductCost -> orderProductCost.getFinalPrice() * orderProductCost.getItemCount()).sum());

    }

    private ArrayList<FoodOrderItem> createFoodItems(int count) {

        ArrayList<FoodOrderItem> items = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            FoodOrderItem food = new FoodOrderItem();
            food.setName(NORMAL.name());
            items.add(food);
        }
        return items;
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

        Film film = new Film();
        film.setTitle(UUID.randomUUID().toString());

        filmShow.setFilm(film);
        return filmShow;
    }

    private PromoOffer createPromoOffer() {
        PromoOffer promoOffer = new PromoOffer();
        promoOffer.setDiscount(10.0f);
        return promoOffer;
    }

    private BasePrice createBasePrice(String type, String subtype) {
        BasePrice basePrice = new BasePrice();
        basePrice.setBasePrice(10);
        basePrice.setItemType(type);
        basePrice.setItemSubtype(subtype);
        return basePrice;
    }

    private List<Chair> getChairs() {
        return cinema.getCinemaHalls().get(0).getChairs();
    }

    private List<Ticket> createTickets(List<Chair> chairs, FilmShow filmShow) {
        return chairs.stream().map(chair -> {
            Ticket ticket = new Ticket();
            ticket.setChair(chair);
            ticket.setFilmShow(filmShow);
            ticket.setPrice(random.nextFloat());
            return ticket;
        }).toList();
    }
}
