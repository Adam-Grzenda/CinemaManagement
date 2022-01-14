package pl.put.CinemaManagement.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.put.CinemaManagement.order.dto.*;
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

    @RolesAllowed("user")
    @PostMapping(value = "/updateOrderState")
    PlacedOrder updateOrderState(@RequestBody OrderStateRequest stateRequest, Principal principal) {
        return orderService.updateOrderState(stateRequest, principal);
    }

    @RolesAllowed("admin")
    @PostMapping(value = "/realizeOrder/{id}")
    PlacedOrder realizeOrder(@PathVariable Long id) {
        return orderService.realizeOrder(id);
    }
}
