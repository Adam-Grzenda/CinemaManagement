package pl.put.CinemaManagement.order.dto;

import lombok.Data;

@Data
public class FoodOrderItemDTO {
    private Long id;
    private String name;
    private Integer amount;
}
