package pl.put.CinemaManagement.order.dto;

import lombok.Data;

@Data
public class OrderProductCost {
    private String type;
    private String subtype;
    private float basePrice;
    private float discount;
    private float finalPrice;
    private Long itemCount;

    public OrderProductCost(String type, String subtype, float discount, Long itemCount) {
        this.type = type;
        this.subtype = subtype;
        this.discount = discount;
        this.itemCount = itemCount;
    }
}
