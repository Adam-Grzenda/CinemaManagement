package pl.put.CinemaManagement.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.put.CinemaManagement.order.dto.Order;
import pl.put.CinemaManagement.order.dto.OrderDisplay;
import pl.put.CinemaManagement.order.dto.OrderProductCost;
import pl.put.CinemaManagement.order.dto.PlacedOrder;
import pl.put.CinemaManagement.order.service.OrderService;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RestController
public class OrderController {

    private final OrderService orderService;

    @RolesAllowed("user")
    @PostMapping(value = "/placeOrder")
    PlacedOrder placeOrder(@RequestBody Order order, Principal principal) {
        return orderService.placeOrder(order, principal);
    }

    @PostMapping(value = "/calculateOrder")
    List<OrderProductCost> calculateOrder(@RequestBody Order order) {
        return orderService.calculateOrderCost(order);
    }

    @RolesAllowed("user")
    @GetMapping(value = "/getUserOrders")
    List<OrderDisplay> getUserOrders(Principal principal) {
        return orderService.getOrdersForUser(principal);
    }

}
