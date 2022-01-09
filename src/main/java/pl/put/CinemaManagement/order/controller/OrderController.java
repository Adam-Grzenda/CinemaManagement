package pl.put.CinemaManagement.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.put.CinemaManagement.model.ClientsOrder;
import pl.put.CinemaManagement.order.dto.Order;
import pl.put.CinemaManagement.order.dto.OrderProductCost;
import pl.put.CinemaManagement.order.exception.BadOrderException;
import pl.put.CinemaManagement.order.service.OrderService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/placeOrder")
    ClientsOrder placeOrder(@RequestBody Order order) throws BadOrderException {
        return orderService.placeOrder(order);
    }

    @GetMapping(value = "/calculateOrder")
    List<OrderProductCost> calculateOrder(@RequestBody Order order) {
        return orderService.calculateOrderCost(order);
    }


}
