package pl.put.CinemaManagement.order.dto;

import lombok.Data;

@Data
public class FoodOrderItem {
    private Long id;
    private String name;
    private Integer amount;
}
