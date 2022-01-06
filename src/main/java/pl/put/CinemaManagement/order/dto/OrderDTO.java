package pl.put.CinemaManagement.order.dto;

import lombok.Data;
import pl.put.CinemaManagement.model.Chair;

import java.util.List;

@Data
public class OrderDTO {
    private Long clientId;
    private List<Chair> chairs;
    private List<FoodOrderItemDTO> foodOrderItems;
    private Long filmShowId;
}
