package pl.put.CinemaManagement.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.put.CinemaManagement.model.Client;
import pl.put.CinemaManagement.order.dto.*;
import pl.put.CinemaManagement.order.service.OrderService;
import pl.put.CinemaManagement.order.service.UserService;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @RolesAllowed("${user.role}")
    @PostMapping(value = "/placeOrder")
    PlacedOrder placeOrder(@RequestBody Order order, Principal principal) {
        Client client = userService.getClientFromProvider(principal);
        return PlacedOrder.of(orderService.placeOrder(order, client));
    }

    @PostMapping(value = "/calculateOrder")
    List<OrderProductCost> calculateOrder(@RequestBody Order order) {
        return orderService.calculateOrderCost(order);
    }

    @RolesAllowed("${user.role}")
    @GetMapping(value = "/getUserOrders")
    List<OrderDisplay> getUserOrders(Principal principal) {
        Client client = userService.getClientFromProvider(principal);
        return orderService.getOrdersForUser(client);
    }

    @RolesAllowed("${user.role}")
    @PostMapping(value = "/updateOrderState")
    PlacedOrder updateOrderState(@RequestBody OrderStateRequest stateRequest, Principal principal) {
        Client client = userService.getClientFromProvider(principal);
        return orderService.updateOrderState(stateRequest, client);
    }

    @RolesAllowed("${admin.role}")
    @PostMapping(value = "/realizeOrder/{id}")
    PlacedOrder realizeOrder(@PathVariable Long id) {
        return orderService.realizeOrder(id);
    }
}
