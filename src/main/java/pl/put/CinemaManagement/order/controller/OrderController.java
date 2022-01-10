package pl.put.CinemaManagement.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.put.CinemaManagement.order.dto.Order;
import pl.put.CinemaManagement.order.dto.OrderProductCost;
import pl.put.CinemaManagement.order.dto.PlacedOrder;
import pl.put.CinemaManagement.order.service.OrderService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/placeOrder")
    PlacedOrder placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @PostMapping(value = "/calculateOrder")
    List<OrderProductCost> calculateOrder(@RequestBody Order order) {
        return orderService.calculateOrderCost(order);
    }


}
