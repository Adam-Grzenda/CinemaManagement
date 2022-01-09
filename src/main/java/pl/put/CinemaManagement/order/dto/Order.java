package pl.put.CinemaManagement.order.dto;

import lombok.Data;
import lombok.ToString;
import pl.put.CinemaManagement.model.Chair;

import java.util.List;

@ToString
@Data
public class Order {
    private Long userId;
    private List<Chair> chairs;
    private List<FoodOrderItem> foodOrderItems;
    private Long filmShowId;

    private Long promoOfferId;
}
