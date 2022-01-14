package pl.put.CinemaManagement.order.dto;

import lombok.Data;

@Data
public class OrderStateRequest {
    private Long orderId;
    private String newState;
}
