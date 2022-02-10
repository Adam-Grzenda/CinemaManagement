package pl.put.CinemaManagement.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.put.CinemaManagement.model.*;
import pl.put.CinemaManagement.order.dto.*;
import pl.put.CinemaManagement.order.exception.BadOrderException;
import pl.put.CinemaManagement.repository.BasePriceRepository;
import pl.put.CinemaManagement.repository.ClientsOrderRepository;
import pl.put.CinemaManagement.repository.FilmShowRepository;
import pl.put.CinemaManagement.repository.PromoOfferRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final ClientsOrderRepository clientsOrderRepository;
    private final FilmShowRepository filmShowRepository;
    private final BasePriceRepository priceRepository;
    private final PromoOfferRepository promoOfferRepository;
    private final UserService userService;


    public PlacedOrder placeOrder(Order order, Principal principal) {
        log.info(order.toString());

        ClientsOrder clientsOrder = new ClientsOrder();
        clientsOrder.setClient(userService.getClientFromProvider(principal));
        clientsOrder.setPaymentType(ClientsOrder.PaymentType.valueOf(order.getPaymentType()));
        clientsOrder.setPaymentStatus(ClientsOrder.PaymentStatus.valueOf(order.getPaymentStatus()));

        FilmShow filmShow = filmShowRepository.findById(order.getFilmShowId()).orElseThrow(() -> {
            throw new BadOrderException("FilmShowId cannot be null");
        });

        Long promoOfferId = order.getPromoOfferId();
        PromoOffer promoOffer = null;
        if (promoOfferId != null) {
            promoOffer = promoOfferRepository.findById(promoOfferId).orElseThrow(() -> {
                throw new BadOrderException("Invalid promo offer id");
            });
        }

        List<Ticket> tickets = new ArrayList<>();

        float discount = promoOffer != null ? promoOffer.getDiscount() : 0;

        Map<String, Float> ticketPrices = this.calculateTicketPrices(order, discount).stream()
                .collect(Collectors.toMap(OrderProductCost::getSubtype, OrderProductCost::getFinalPrice));

        if (order.getChairs() != null) {
            for (Chair chair : order.getChairs()) {
                log.info(chair.toString());
                Ticket ticket = new Ticket();
                ticket.setChair(chair);
                ticket.setFilmShow(filmShow);
                ticket.setPromoOffer(promoOffer);
                ticket.setClientsOrder(clientsOrder);
                ticket.setPrice(ticketPrices.get(chair.getChairType().toString()));
                tickets.add(ticket);
            }
        }

        clientsOrder.setTickets(tickets);

        clientsOrder.setAmount(calculateTotalCost(order));

        return PlacedOrder.of(clientsOrderRepository.save(clientsOrder));
    }

    public List<OrderDisplay> getOrdersForUser(Principal principal) {
        Client client = userService.getClientFromProvider(principal);
        return clientsOrderRepository.getClientsOrderByClient(client)
                .stream().map(OrderDisplay::fromClientsOrder)
                .collect(Collectors.toList());
    }

    public PlacedOrder updateOrderState(OrderStateRequest stateRequest, Principal principal) {
        ClientsOrder clientsOrder = clientsOrderRepository.findClientsOrderByClientAndId(
                        userService.getClientFromProvider(principal), stateRequest.getOrderId())
                .orElseThrow(() -> {
                    throw new BadOrderException("Order for given Id does not exist");
                });

        /*
        Normally we would get the payment status update from the payment gateway,
        however for the sole purpose of demonstration we're basically trusting the client
        TODO - implement a microservice mocking the payment provider
         */

        try {
            ClientsOrder.PaymentStatus requestedStatus = ClientsOrder.PaymentStatus.valueOf(stateRequest.getNewState());
            clientsOrder.updatePaymentState(requestedStatus);
        } catch (IllegalStateException exception) {
            log.error("Order payment status update failed");
            return PlacedOrder.failed(clientsOrder);
        }

        return PlacedOrder.of(clientsOrderRepository.save(clientsOrder));
    }


    private double calculateTotalCost(Order order) {
        return this.calculateOrderCost(order).stream()
                .collect(Collectors.summarizingDouble(a -> a.getFinalPrice() * a.getItemCount())).getSum();
    }


    public List<OrderProductCost> calculateOrderCost(Order order) {

        Long promoOfferId = order.getPromoOfferId();
        float discount = getDiscount(promoOfferId);

        List<OrderProductCost> orderCosts = calculateTicketPrices(order, discount);

        if (order.getFoodOrderItems() != null) {
            orderCosts.addAll(calculateFoodProductsPrices(order, discount));
        }

        return orderCosts;
    }

    private float getDiscount(Long promoOfferId) {
        if (promoOfferId != null) {
            return promoOfferRepository
                    .findById(promoOfferId)
                    .map(PromoOffer::getDiscount)
                    .orElseThrow(() -> {
                        throw new BadOrderException("Invalid promo ID");
                    });
        } else {
            return 0;
        }
    }

    //TODO common interface for these product types would be useful
    private List<OrderProductCost> calculateTicketPrices(Order order, float discount) {
        return order.getChairs().stream()
                .collect(Collectors.groupingBy(Chair::getChairType, Collectors.counting()))
                .entrySet()
                .stream()
                .map(e -> getProductCost("Chair", e.getKey().toString(), discount, e.getValue()))
                .collect(Collectors.toList());
    }

    private List<OrderProductCost> calculateFoodProductsPrices(Order order, float discount) {
        return order.getFoodOrderItems().stream()
                .collect(Collectors.groupingBy(FoodOrderItem::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .map(e -> getProductCost("Food", e.getKey(), discount, e.getValue()))
                .collect(Collectors.toList());
    }


    private OrderProductCost getProductCost(String type, String subtype, float discount, Long itemCount) {
        OrderProductCost productCostDTO = new OrderProductCost(type, subtype, discount, itemCount);

        float basePrice = priceRepository.findByItemTypeAndItemSubtype(type, subtype)
                .orElseThrow(
                        () -> {
                            throw new BadOrderException("Invalid product type/subtype");
                        }
                ).getBasePrice();

        productCostDTO.setBasePrice(basePrice);
        productCostDTO.setFinalPrice(basePrice * (100 - discount) / 100);

        return productCostDTO;
    }

    public PlacedOrder realizeOrder(Long id) {
        ClientsOrder clientsOrder = clientsOrderRepository.findById(id)
                .orElseThrow(() -> {
                    throw new BadOrderException("Invalid orderId");
                });

        clientsOrder.realizeOrder();

        return PlacedOrder.of(clientsOrderRepository.save(clientsOrder));
    }
}
