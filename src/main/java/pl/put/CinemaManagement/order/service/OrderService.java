package pl.put.CinemaManagement.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.put.CinemaManagement.model.*;
import pl.put.CinemaManagement.order.dto.FoodOrderItem;
import pl.put.CinemaManagement.order.dto.Order;
import pl.put.CinemaManagement.order.dto.OrderProductCost;
import pl.put.CinemaManagement.order.exception.BadOrderException;
import pl.put.CinemaManagement.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final ClientsOrderRepository clientsOrderRepository;
    private final ClientRepository clientRepository;
    private final FilmShowRepository filmShowRepository;
    private final BasePriceRepository priceRepository;
    private final PromoOfferRepository promoOfferRepository;

    public ClientsOrder placeOrder(Order order) {
        log.info(order.toString());
        ClientsOrder clientsOrder = new ClientsOrder();

        Client client = clientRepository.findById(order.getUserId()).orElseThrow(() -> {
            throw new BadOrderException("User ID cannot be null");
        });
        clientsOrder.setClient(client);

        FilmShow filmShow = filmShowRepository.findById(order.getFilmShowId()).orElseThrow(() -> {
            throw new BadOrderException("FilmShowId cannot be null");
        });

        List<Ticket> tickets = new ArrayList<>();

        log.info("Tickets: ");
        if (order.getChairs() != null) {
            for (Chair chair : order.getChairs()) {
                Ticket ticket = new Ticket();
                ticket.setChair(chair);
                ticket.setFilmShow(filmShow);
                //TODO set promo offer
                log.info(ticket.toString());
            }
        }
        //TODO assign tickets to order

        clientsOrder.setAmount(calculateTotalCost(order));

        return clientsOrderRepository.save(clientsOrder);
    }

    private double calculateTotalCost(Order order) {
        return this.calculateOrderCost(order).stream()
                .collect(Collectors.summarizingDouble(OrderProductCost::getFinalPrice)).getSum();
    }


    public List<OrderProductCost> calculateOrderCost(Order order) {

        Long promoOfferId = order.getPromoOfferId();
        float discount = getDiscount(promoOfferId);

        List<OrderProductCost> orderCosts = calculateTicketPrices(order, discount);

        if (!order.getFoodOrderItems().isEmpty()) {
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
        productCostDTO.setDiscount(discount);
        productCostDTO.setFinalPrice(basePrice - basePrice * discount);

        return productCostDTO;
    }
}
